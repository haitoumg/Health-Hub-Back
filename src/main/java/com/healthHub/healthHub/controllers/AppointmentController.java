package com.healthHub.healthHub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.healthHub.healthHub.classes.AppointmentInfos;
import com.healthHub.healthHub.classes.CalendarInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthHub.healthHub.classes.AppointmentRequer;
import com.healthHub.healthHub.model.Calendar;
import com.healthHub.healthHub.model.Employee;
import com.healthHub.healthHub.model.Appointment;
import com.healthHub.healthHub.repository.CalenderRepository;
import com.healthHub.healthHub.repository.EmployeeRepository;
import com.healthHub.healthHub.repository.AppointmentRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class AppointmentController {
	private final CalenderRepository callendrierRepository;
	private final EmployeeRepository employeeRepository;
	private final AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentController(CalenderRepository callendarRepository,EmployeeRepository employeeRepository,AppointmentRepository appointmentRepository) {
		this.employeeRepository = employeeRepository;
		this.callendrierRepository = callendarRepository;
		this.appointmentRepository = appointmentRepository;
	}


	@PostMapping("/appointment")
	public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequer appRequ) {
		Appointment app=new Appointment();
		app.setDateAppointment(appRequ.getDateAppointment());
		app.setCancelled(false);
		app.setCancelledByDoctor(false);
		long idE=appRequ.getEmployeeId();
		long idC=appRequ.getCalendarId();
		Optional<Employee> optionalE = employeeRepository.findById(idE);
	    if (optionalE.isPresent()) { 
	    	app.setEmployee((optionalE.get()));
	    }
	    Optional<Calendar> optionalC = callendrierRepository.findById(idC);
	    if (optionalC.isPresent()) {
	    	app.setCalendar((optionalC.get()));
	    }
	    Appointment createdrv = appointmentRepository.save(app);
		return new ResponseEntity<>(createdrv, HttpStatus.CREATED);
	}
	
	@PostMapping("/appointments")
	public ResponseEntity<List<AppointmentInfos>> getAllAppointments(@RequestBody Map<String, Integer> requestObject) {
		int personneId=requestObject.get("id");
	    List<Appointment> appointments = appointmentRepository.findAllByEmployeePersonneId(personneId);
		List<AppointmentInfos> appointmentsInfos=new ArrayList<>();
		for (int i = 0; i < appointments.size(); i++) {
			Appointment appointment = appointments.get(i);
			String cancelledBy=appointment.isCancelled()?(appointment.isCancelledByDoctor()?"The doctor":"you"):null;
			AppointmentInfos appointmentInfos=new AppointmentInfos((appointment.getCalendar().getworkingDay()).toString().substring(0,10)+" "+appointment.getCalendar().getstartTime().toString(),(appointment.getCalendar().getworkingDay()).toString().substring(0,10)+" "+ appointment.getCalendar().getendTime().toString(),"RDV with Dr "+appointment.getCalendar().getDoctor().getfirstName()+" "+appointment.getCalendar().getDoctor().getlastName(), appointment.getCalendar().getDoctor().getHub().getHubName(), appointment.getAppointmentId(), appointment.isCancelled(), cancelledBy);
			appointmentsInfos.add(appointmentInfos);
		}

	    return new ResponseEntity<>(appointmentsInfos, HttpStatus.OK);
	}
	@PostMapping("/appointmentsByDoctor")
	public ResponseEntity<List<AppointmentInfos>> getAllAppointmentsByDoctor(@RequestBody Map<String, Integer> requestObject) {
		int personneId=requestObject.get("id");
		List<Appointment> appointments = appointmentRepository.findAllByCalendarDoctorPersonneId(personneId);
		List<AppointmentInfos> appointmentsInfos=new ArrayList<>();
		for (int i = 0; i < appointments.size(); i++) {
			Appointment appointment = appointments.get(i);
			String cancelledBy=appointment.isCancelled()?(appointment.isCancelledByDoctor()?"you":"The employee"):null;
			AppointmentInfos appointmentInfos=new AppointmentInfos((appointment.getCalendar().getworkingDay()).toString().substring(0,10)+" "+appointment.getCalendar().getstartTime().toString(),(appointment.getCalendar().getworkingDay()).toString().substring(0,10)+" "+ appointment.getCalendar().getendTime().toString(),appointment.getAppointmentId(),appointment.getEmployee().getfirstName()+ " " +appointment.getEmployee().getlastName(), appointment.isCancelled(), cancelledBy);
			appointmentsInfos.add(appointmentInfos);
		}

		return new ResponseEntity<>(appointmentsInfos, HttpStatus.OK);
	}
	@GetMapping("/availableAppointments")
	public ResponseEntity<List<Appointment>> getAvailableAppointments(@RequestBody int personneId) {
		List<Appointment> appointments = appointmentRepository.findAllByCancelledAndCalendarDoctorPersonneId(false, personneId);
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
	@GetMapping("/findAll")
	public List<Appointment> getAllAppointmentByHubAndIsCancelled(
			@RequestParam("what_hub_reservation") String whatHubReservation,
			@RequestParam("is_cancelled") boolean isCancelled
	) {
		return appointmentRepository.findAllByCalendarDoctorHubHubNameAndCancelled(whatHubReservation, isCancelled);
	}
	@GetMapping("/appointment/{id}")
	public ResponseEntity<Appointment> getCalendrierById(@PathVariable("id") Long id) {
		Optional<Appointment> rv = appointmentRepository.findById(id);
		if (rv.isPresent()) {
			return new ResponseEntity<>(rv.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> updateDCalendrier(@PathVariable("id") Long id, @RequestBody AppointmentRequer appRequ) {
	    Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
	    if (optionalAppointment.isPresent()) {
	    	Appointment appointment = optionalAppointment.get();

			appointment.setDateAppointment(appRequ.getDateAppointment());
			appointment.setCancelled(false);
			long idE=appRequ.getEmployeeId();
			long idC=appRequ.getCalendarId();
			Optional<Employee> optionalE = employeeRepository.findById(idE);
		    if (optionalE.isPresent()) {
				appointment.setEmployee((optionalE.get()));
		    }
		    Optional<Calendar> optionalC = callendrierRepository.findById(idC);
		    if (optionalC.isPresent()) {
				appointment.setCalendar((optionalC.get()));
		    }
		    Appointment savedApp = appointmentRepository.save(appointment);
	        return new ResponseEntity<>(savedApp, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}*/
	
	@PutMapping("/appointment")
	public ResponseEntity<Void> cancellAppointment(@RequestBody Map<String, Long> requestObject) {

	    Optional<Appointment> optionalAppointment = appointmentRepository.findById(requestObject.get("id"));
	    if (optionalAppointment.isPresent()) {
			//The appointment won't be deleted from DB but cancelled with specifying who cancelled
			optionalAppointment.get().setCancelled(true);
			optionalAppointment.get().setCancelledByDoctor(false);
			Appointment appointment= appointmentRepository.save(optionalAppointment.get());
	    	//appointmentRepository.delete(optionalEmploye.get());
			return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}

