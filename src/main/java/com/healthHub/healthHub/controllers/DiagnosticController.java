package com.healthHub.healthHub.controllers;


import java.util.Date;
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
import com.healthHub.healthHub.classes.DiagnosticRequer;
import com.healthHub.healthHub.model.Diagnostic;
import com.healthHub.healthHub.model.Doctor;
import com.healthHub.healthHub.model.Employee;
import com.healthHub.healthHub.repository.DiagnosticRepository;
import com.healthHub.healthHub.repository.DoctorRepository;
import com.healthHub.healthHub.repository.EmployeeRepository;

/*
*import javax.tools.Diagnostic;
*/

@RestController
@CrossOrigin("http://localhost:3000")
public class DiagnosticController {
	private final DoctorRepository doctorRepository;
	private final EmployeeRepository employeeRepository;
	private final DiagnosticRepository diagnosticRepository;

	@Autowired
	public DiagnosticController(EmployeeRepository employeeRepository, DoctorRepository doctorRepository,
			DiagnosticRepository diagnosticRepository) {
		this.employeeRepository = employeeRepository;
		this.doctorRepository = doctorRepository;
		this.diagnosticRepository = diagnosticRepository;
	}

	@PostMapping("/diagnostic")
	public ResponseEntity<Diagnostic> createDiagnostic(@RequestBody DiagnosticRequer DiagnosticRequ) {
		Diagnostic app = new Diagnostic();
		app.setNote(DiagnosticRequ.getNote());
		long idE = DiagnosticRequ.getEmployeeId();
		long idD = DiagnosticRequ.getDoctorId();
		Optional<Employee> optionalE = employeeRepository.findById(idE);
		if (optionalE.isPresent()) {
			app.setEmployee((optionalE.get()));
		}
		Optional<Doctor> optionalD = doctorRepository.findById(idD);
		if (optionalD.isPresent()) {
			app.setDoctor((optionalD.get()));
		}
		app.setDiagnosticDate((Date) java.util.Calendar.getInstance().getTime());
		Diagnostic createdApp = diagnosticRepository.save(app);
		return new ResponseEntity<>(createdApp, HttpStatus.CREATED);
	}

	@GetMapping("/diagnostics")
	public ResponseEntity<List<Diagnostic>> getAllDiagnostics() {
		List<Diagnostic> diagnostics = diagnosticRepository.findAll();
		return new ResponseEntity<>(diagnostics, HttpStatus.OK);
	}

	@GetMapping("/diagnostic/{id}")
	public ResponseEntity<Diagnostic> getCalendrierById(@PathVariable("id") Long id) {
		Optional<Diagnostic> app = diagnosticRepository.findById(id);
		if (app.isPresent()) {
			return new ResponseEntity<>(app.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/diagnosticByDoctor/{id}")
	public ResponseEntity<List<Diagnostic>> getDiadnosticByDoctor(@PathVariable("id") Long id) {
		Optional<Doctor> d=doctorRepository.findById(id);
		Optional<List<Diagnostic>> app = diagnosticRepository.findAllBydoctor(d.get());
		if (app.isPresent()) {
			return new ResponseEntity<>(app.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/diagnosticByEmployeeAndDoctor/{id}/{ide}")
	public ResponseEntity<List<Diagnostic>> getDiadnosticByEmployeeAndDoctor(@PathVariable("id") Long id,@PathVariable("ide") Long ide) {
		Optional<Employee> e=employeeRepository.findById(ide);
		Optional<Doctor> d=doctorRepository.findById(id);
		Optional<List<Diagnostic>> app = diagnosticRepository.findAllByemployeeAndDoctor(e.get(),d.get());
		if (app.isPresent()) {
			return new ResponseEntity<>(app.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/diagnostic/{id}")
	public ResponseEntity<Diagnostic> updateDDiagnostic(@PathVariable("id") Long id,
			@RequestBody DiagnosticRequer DiagnosticRequ) {
		Optional<Diagnostic> optionalD = diagnosticRepository.findById(id);
		if (optionalD.isPresent()) {
			Diagnostic existingD = optionalD.get();
			existingD.setNote(DiagnosticRequ.getNote());
			long idE = DiagnosticRequ.getEmployeeId();
			long idD = DiagnosticRequ.getDoctorId();
			Optional<Employee> optionalE = employeeRepository.findById(idE);
			if (optionalE.isPresent()) {
				existingD.setEmployee((optionalE.get()));
			}
			Optional<Doctor> optionalDoc = doctorRepository.findById(idD);
			if (optionalDoc.isPresent()) {
				existingD.setDoctor((optionalDoc.get()));
			}
			Diagnostic savedDiagnostic = diagnosticRepository.save(existingD);
			return new ResponseEntity<>(savedDiagnostic, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/diagnostic/{id}")
	public ResponseEntity<Void> deleteDiagnostic(@PathVariable("id") Long id) {
		Optional<Diagnostic> optionalEmploye = diagnosticRepository.findById(id);
		if (optionalEmploye.isPresent()) {
			diagnosticRepository.delete(optionalEmploye.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
