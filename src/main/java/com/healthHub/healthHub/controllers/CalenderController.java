package com.healthHub.healthHub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.classes.CalendarRequer;
import com.healthHub.healthHub.model.Calendar;
import com.healthHub.healthHub.model.Doctor;
import com.healthHub.healthHub.repository.CalenderRepository;
import com.healthHub.healthHub.repository.DoctorRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class CalenderController {
	private final CalenderRepository calenderRepository;
	private final DoctorRepository doctorRepository;

	@Autowired
	public CalenderController(CalenderRepository callendrierRepository,DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
		this.calenderRepository = callendrierRepository;
	}


	@PostMapping("/calendar")
	public ResponseEntity<Calendar> createCalendrier(@RequestBody CalendarRequer calendrierRequ) {
		Calendar calendrier=new Calendar();
		calendrier.setstartTime(calendrierRequ.getstartTime());
		calendrier.setendTime(calendrierRequ.getendTime());
		calendrier.setworkingDay(calendrierRequ.getworkingDay());

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
	    Optional<Calendar> optionalDoctor = calenderRepository.findById(id);
	    if (optionalDoctor.isPresent()) {
	    	calenderRepository.delete(optionalDoctor.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}

