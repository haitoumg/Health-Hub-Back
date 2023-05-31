package com.healthHub.healthHub.controllers;

import java.util.List;
import java.util.Optional;

import javax.tools.Diagnostic;

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

import com.healthHub.healthHub.classes.AppointmentRequer;
import com.healthHub.healthHub.classes.DiagnosticRequer;
import com.healthHub.healthHub.model.Calendar;
import com.healthHub.healthHub.model.Doctor;
import com.healthHub.healthHub.model.Employee;
import com.healthHub.healthHub.model.diagnostic;
import com.healthHub.healthHub.model.Appointment;
import com.healthHub.healthHub.repository.CalenderRepository;
import com.healthHub.healthHub.repository.DiagnosticRepository;
import com.healthHub.healthHub.repository.DoctorRepository;
import com.healthHub.healthHub.repository.EmployeeRepository;
import com.healthHub.healthHub.repository.AppointmentRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class DiagnosticController {
	private final DoctorRepository doctorRepository;
	private final EmployeeRepository employeeRepository;
	private final DiagnosticRepository diagnosticRepository;

	@Autowired
	public DiagnosticController(EmployeeRepository employeeRepository,DoctorRepository doctorRepository, DiagnosticRepository diagnosticRepository) {
		this.employeeRepository = employeeRepository;
		this.doctorRepository = doctorRepository;
		this.diagnosticRepository = diagnosticRepository;
	}


	@PostMapping("/diagnostic")
	public ResponseEntity<diagnostic> createDiagnostic(@RequestBody DiagnosticRequer DiagnosticRequ) {
		diagnostic app=new diagnostic();
		app.setNote(DiagnosticRequ.getNote());
		long idE=DiagnosticRequ.getEmployeeId();
		long idD=DiagnosticRequ.getDoctorId();
		Optional<Employee> optionalE = employeeRepository.findById(idE);
	    if (optionalE.isPresent()) { 
	    	app.setEmployee((optionalE.get()));
	    }
	    Optional<Doctor> optionalD = doctorRepository.findById(idD);
	    if (optionalD.isPresent()) {
	    	app.setDoctor((optionalD.get()));
	    }
	    diagnostic createdrv = diagnosticRepository.save(app);
		return new ResponseEntity<>(createdrv, HttpStatus.CREATED);
	}
	
	@GetMapping("/diagnostics")
	public ResponseEntity<List<diagnostic>> getAllDiagnostics() {
	    List<diagnostic> calendars = diagnosticRepository.findAll();
	    return new ResponseEntity<>(calendars, HttpStatus.OK);
	}
	
	@GetMapping("/diagnostic/{id}")
	public ResponseEntity<diagnostic> getCalendrierById(@PathVariable("id") Long id) {
		Optional<diagnostic> rv = diagnosticRepository.findById(id);
		if (rv.isPresent()) {
			return new ResponseEntity<>(rv.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/diagnostic/{id}")
	public ResponseEntity<diagnostic> updateDDiagnostic(@PathVariable("id") Long id, @RequestBody DiagnosticRequer DiagnosticRequ) {
	    Optional<diagnostic> optionalD = diagnosticRepository.findById(id);
	    if (optionalD.isPresent()) {
	    	diagnostic existingD = optionalD.get();		    
		    existingD.setNote(DiagnosticRequ.getNote());
			long idE=DiagnosticRequ.getEmployeeId();
			long idD=DiagnosticRequ.getDoctorId();
			Optional<Employee> optionalE = employeeRepository.findById(idE);
		    if (optionalE.isPresent()) { 
		    	existingD.setEmployee((optionalE.get()));
		    }
		    Optional<Doctor> optionalDoc = doctorRepository.findById(idD);
		    if (optionalDoc.isPresent()) {
		    	existingD.setDoctor((optionalDoc.get()));
		    }
		    diagnostic savedDiagnostic = diagnosticRepository.save(existingD);
	        return new ResponseEntity<>(savedDiagnostic, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/diagnostic/{id}")
	public ResponseEntity<Void> deleteDiagnostic(@PathVariable("id") Long id) {
	    Optional<diagnostic> optionalEmploye = diagnosticRepository.findById(id);
	    if (optionalEmploye.isPresent()) {
	    	diagnosticRepository.delete(optionalEmploye.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}


