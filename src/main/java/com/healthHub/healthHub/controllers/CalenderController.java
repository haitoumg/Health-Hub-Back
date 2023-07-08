package com.healthHub.healthHub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.healthHub.healthHub.classes.AppointmentInfos;
import com.healthHub.healthHub.classes.CalendarInfos;
import com.healthHub.healthHub.model.Appointment;
import com.healthHub.healthHub.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthHub.healthHub.classes.CalendarRequer;
import com.healthHub.healthHub.model.Calendar;
import com.healthHub.healthHub.model.Doctor;
import com.healthHub.healthHub.repository.CalenderRepository;
import com.healthHub.healthHub.repository.DoctorRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class CalenderController {
	private final CalenderRepository calenderRepository;

	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;

	@Autowired
	public CalenderController(CalenderRepository callendrierRepository,DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
		this.doctorRepository = doctorRepository;
		this.calenderRepository = callendrierRepository;
		this.appointmentRepository=appointmentRepository;
	}


	@PostMapping("/calendar")
	public ResponseEntity<Calendar> createCalendrier(@RequestBody CalendarRequer calendrierRequ) {
		Calendar calendrier=new Calendar();
		calendrier.setstartTime(calendrierRequ.getstartTime());
		calendrier.setendTime(calendrierRequ.getendTime());
		calendrier.setworkingDay(calendrierRequ.getworkingDay());
		calendrier.setText(calendrierRequ.getText());
		long id=calendrierRequ.getDoctorId();
		Optional<Doctor> optionalHub = doctorRepository.findById(id);
	    if (optionalHub.isPresent()) {
	    	calendrier.setDoctor((optionalHub.get()));
	    }
	    Calendar createdCalendrier = calenderRepository.save(calendrier);
		return new ResponseEntity<>(createdCalendrier, HttpStatus.CREATED);
	}
	
	@GetMapping("/calendars")
	public ResponseEntity<List<Calendar>> getAllCalendriers() {
	    List<Calendar> calendriers = calenderRepository.findAll();
	    return new ResponseEntity<>(calendriers, HttpStatus.OK);
	}
	@PostMapping("/calendarsAvailableByDoctor")
	public ResponseEntity<List<CalendarInfos>> getAllCalendarsAvailableByDoctor(@RequestBody Map<String, Integer> requestObject) {
		List<Calendar> calendars = calenderRepository.findAllByDoctorPersonneId(requestObject.get("id"));
		List<CalendarInfos> calendarsInfos=new ArrayList<>();
		for (int i = 0; i < calendars.size(); i++) {
			CalendarInfos calendarInfos=null;
			Calendar calendar = calendars.get(i);
			Optional<Appointment> possibleAppointment0=appointmentRepository.findByCancelledByDoctorAndCalendarCalendarId(true, calendar.getCalendarId());
			if(possibleAppointment0.isPresent()){
				continue;
			}
			Optional<Appointment> possibleAppointment=appointmentRepository.findByCalendarCalendarIdAndCancelled(calendar.getCalendarId(), false);
			if(possibleAppointment.isPresent()){
				continue;
			}

			calendarInfos=new CalendarInfos(calendar.getworkingDay().toString().substring(0,10)+" "+calendar.getstartTime().toString(), calendar.getworkingDay().toString().substring(0,10)+" "+calendar.getendTime().toString());

			calendarInfos.setCalendarId(calendar.getCalendarId());
			calendarsInfos.add(calendarInfos);
		}
		return new ResponseEntity<>(calendarsInfos, HttpStatus.OK);
	}

	@PostMapping("/calendarsInfosByDoctor")
	public ResponseEntity<List<CalendarInfos>> getAllCalendarsInfosByDoctor(@RequestBody Map<String, Integer> requestObject) {
		List<Calendar> calendars = calenderRepository.findAllByDoctorPersonneId(requestObject.get("id"));
		List<CalendarInfos> calendarsInfos=new ArrayList<>();
		for (int i = 0; i < calendars.size(); i++) {
			CalendarInfos calendarInfos=null;
			Calendar calendar = calendars.get(i);
			Optional<Appointment> possibleAppointment0=appointmentRepository.findByCancelledByDoctorAndCalendarCalendarId(true, calendar.getCalendarId());
			if(possibleAppointment0.isPresent()){
				continue;
			}
			Optional<Appointment> possibleAppointment=appointmentRepository.findByCalendarCalendarIdAndCancelled(calendar.getCalendarId(), false);
			if(possibleAppointment.isPresent()){
				Appointment appointment=possibleAppointment.get();
				calendarInfos=new CalendarInfos(calendar.getworkingDay(), calendar.getstartTime(), calendar.getendTime(), appointment.getEmployee().getlastName(), appointment.getEmployee().getfirstName(), true);
			}
			else {
				calendarInfos=new CalendarInfos(calendar.getworkingDay(), calendar.getstartTime(), calendar.getendTime(), null, null, false);
			}
			calendarInfos.setCalendarId(calendar.getCalendarId());
			calendarsInfos.add(calendarInfos);
		}
		return new ResponseEntity<>(calendarsInfos, HttpStatus.OK);
	}
	@PostMapping("calendarsInfosByEmployee")
	public ResponseEntity<List<CalendarInfos>> getAllCalendarsInfosByEmployee(@RequestBody Map<String, String> requestObject) {
		List<Calendar> calendars = calenderRepository.findByDoctorHubCity(requestObject.get("city"));

		int personneId= Integer.parseInt(requestObject.get("personneId"));

		List<CalendarInfos> calendarsInfos=new ArrayList<>();
		for (int i = 0; i < calendars.size(); i++) {
			CalendarInfos calendarInfos=null;
			Calendar calendar = calendars.get(i);
			Optional<Appointment> possibleAppointment0=appointmentRepository.findByCancelledByDoctorAndCalendarCalendarId(true, calendar.getCalendarId());
			if(possibleAppointment0.isPresent()){
				continue;
			}
			Optional<Appointment> possibleAppointment=appointmentRepository.findByCalendarCalendarIdAndCancelled(calendar.getCalendarId(), false);
			if(possibleAppointment.isPresent() && possibleAppointment.get().getEmployee().getPersonneId() == personneId){
				Appointment appointment=possibleAppointment.get();
				calendarInfos=new CalendarInfos(calendar.getworkingDay(), calendar.getstartTime(), calendar.getendTime(), "Reserved for you", " ", true);

			}else if(possibleAppointment.isPresent() && possibleAppointment.get().getEmployee().getPersonneId() != personneId){
				continue;
			}
			else {
				calendarInfos=new CalendarInfos(calendar.getworkingDay(), calendar.getstartTime(), calendar.getendTime(), null, null, false);

			}
			calendarInfos.setCalendarId(calendar.getCalendarId());
			calendarsInfos.add(calendarInfos);
		}
		return new ResponseEntity<>(calendarsInfos, HttpStatus.OK);
	}
		@GetMapping("/calendarsByHub")
	public ResponseEntity<List<Calendar>> getAvailableCalendriers(@RequestParam("city") String city) {
		List<Calendar> calendriers = calenderRepository.findByDoctorHubCity(city);
		return new ResponseEntity<>(calendriers, HttpStatus.OK);
	}

	@GetMapping("/calendar/{id}")
	public ResponseEntity<Calendar> getCalendrierById(@PathVariable("id") Long id) {
		Optional<Calendar> calendrier = calenderRepository.findById(id);
		if (calendrier.isPresent()) {
			return new ResponseEntity<>(calendrier.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/calendar/{id}")
	public ResponseEntity<Calendar> updateDCalendrier(@PathVariable("id") Long id, @RequestBody CalendarRequer updatedDoctor) {
	    Optional<Calendar> optionalCalendrier = calenderRepository.findById(id);
	    if (optionalCalendrier.isPresent()) {
	    	Calendar existingCalendrier = optionalCalendrier.get();
	    	existingCalendrier.setstartTime(updatedDoctor.getstartTime());
	    	existingCalendrier.setendTime(updatedDoctor.getendTime());
	    	existingCalendrier.setworkingDay(updatedDoctor.getworkingDay());
			existingCalendrier.setText(updatedDoctor.getText());
			long idD=updatedDoctor.getDoctorId();
			Optional<Doctor> optionalHub = doctorRepository.findById(idD);
		    if (optionalHub.isPresent()) {
		    	existingCalendrier.setDoctor((optionalHub.get()));
		    }
		    Calendar savedCalendar = calenderRepository.save(existingCalendrier);
	        return new ResponseEntity<>(savedCalendar, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/calendar/{id}")
	public ResponseEntity<Void> deleteCalendrier(@PathVariable("id") Long id) {
	    Optional<Calendar> optionalCalender = calenderRepository.findById(id);
	    if (optionalCalender.isPresent()) {
			Calendar calendar=optionalCalender.get();
			Optional<Appointment> optionalAppointment=appointmentRepository.findByCalendarCalendarId(calendar.getCalendarId());
			if(optionalAppointment.isPresent()){
				Appointment appointment=optionalAppointment.get();
				appointment.setCancelled(true);
				appointment.setCancelledByDoctor(true);
				appointmentRepository.save(appointment);
			}else {
				calenderRepository.delete(calendar);
			}

	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@GetMapping("/Casablanca")
	public List<Calendar> getReservationsByUserRoleAndHubCasa() {
		return calenderRepository.findByDoctorRoleAndDoctorHubHubName("doctor", "Casablanca");
	}

	@GetMapping("/Tetouan")
	public List<Calendar> getReservationsByUserRoleAndHubTet() {
		return calenderRepository.findByDoctorRoleAndDoctorHubHubName("doctor", "Tetouan");
	}
}

