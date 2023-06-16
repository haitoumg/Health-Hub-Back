package com.healthHub.healthHub.controllers;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<Appointment> createCalendrier(@RequestBody AppointmentRequer appRequ) {
		Appointment app=new Appointment();
		app.setDateAppointment(appRequ.getDateAppointment());
		app.setCancelled(false);
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
	
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllCalendriers() {
	    List<Appointment> appointments = appointmentRepository.findAll();
	    return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
	@GetMapping("/availableAppointments")
	public ResponseEntity<List<Appointment>> getAvailableAppointments(@RequestParam("personneId") int personneId) {
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

	@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> updateDCalendrier(@PathVariable("id") Long id, @RequestBody AppointmentRequer appRequ) {
	    Optional<Appointment> optionalrv = appointmentRepository.findById(id);
	    if (optionalrv.isPresent()) {
	    	Appointment existingrv = optionalrv.get();
	    	
	    	existingrv.setDateAppointment(appRequ.getDateAppointment());
	    	existingrv.setCancelled(false);
			long idE=appRequ.getEmployeeId();
			long idC=appRequ.getCalendarId();
			Optional<Employee> optionalE = employeeRepository.findById(idE);
		    if (optionalE.isPresent()) {
		    	existingrv.setEmployee((optionalE.get()));
		    }
		    Optional<Calendar> optionalC = callendrierRepository.findById(idC);
		    if (optionalC.isPresent()) {
		    	existingrv.setCalendar((optionalC.get()));
		    }
		    Appointment savedApp = appointmentRepository.save(existingrv);
	        return new ResponseEntity<>(savedApp, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<Void> deleteCalendrier(@PathVariable("id") Long id) {
	    Optional<Appointment> optionalEmploye = appointmentRepository.findById(id);
	    if (optionalEmploye.isPresent()) {
	    	appointmentRepository.delete(optionalEmploye.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}

