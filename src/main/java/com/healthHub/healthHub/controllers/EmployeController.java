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

import com.healthHub.healthHub.classes.EmployeRequer;
import com.healthHub.healthHub.model.Centre;
import com.healthHub.healthHub.model.Employe;
import com.healthHub.healthHub.repository.CentreRepository;
import com.healthHub.healthHub.repository.EmployeRepository;


@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeController {
	private final EmployeRepository employeRepository;
	private final CentreRepository CenterRepository;

	@Autowired
	public EmployeController(EmployeRepository employeRepository,CentreRepository CenterRepository) {
		this.CenterRepository = CenterRepository;
		this.employeRepository = employeRepository;
	}

	@PostMapping("/employe")
	public ResponseEntity<Employe> createEmploye(@RequestBody EmployeRequer EmployeRequ) {

		Employe Employe=new Employe(EmployeRequ.getNumEmploye());
		Employe.setEmail(EmployeRequ.getEmail());
		Employe.setDateNaissance(EmployeRequ.getDateNaissance());
		Employe.setMotDePasse(EmployeRequ.getMotDePasse());
		Employe.setNom(EmployeRequ.getNom());
		Employe.setPrenom(EmployeRequ.getPrenom());
		Employe.setRole("Employe");
		Employe.setTelephone(EmployeRequ.getTelephone());
		long id=EmployeRequ.getCentre();
		Optional<Centre> optionalCentre = CenterRepository.findById(id);
	    if (optionalCentre.isPresent()) {
	    	Employe.setCentre(optionalCentre.get());
	    }
		Employe createdEmploye = employeRepository.save(Employe);
		return new ResponseEntity<>(createdEmploye, HttpStatus.CREATED);
	}
	
	@GetMapping("/employes")
	public ResponseEntity<List<Employe>> getAllEmployes() {
	    List<Employe> Employes = employeRepository.findAll();
	    return new ResponseEntity<>(Employes, HttpStatus.OK);
	}
	
	@GetMapping("/employe/{id}")
	public ResponseEntity<Employe> getEmployeById(@PathVariable("id") Long id) {
		Optional<Employe> Employe = employeRepository.findById(id);
		if (Employe.isPresent()) {
			return new ResponseEntity<>(Employe.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/employe/{id}")
	public ResponseEntity<Employe> updateEmploye(@PathVariable("id") Long id, @RequestBody EmployeRequer updatedEmploye) {
	    Optional<Employe> optionalEmploye = employeRepository.findById(id);
	    if (optionalEmploye.isPresent()) {
	    	Employe existingEmploye = optionalEmploye.get();
	    	existingEmploye.setNumEmploye(updatedEmploye.getNumEmploye());
	        existingEmploye.setNom(updatedEmploye.getNom());
	        existingEmploye.setPrenom(updatedEmploye.getPrenom());
	        existingEmploye.setDateNaissance(updatedEmploye.getDateNaissance());
	        existingEmploye.setTelephone(updatedEmploye.getTelephone());
	        existingEmploye.setEmail(updatedEmploye.getEmail());
	        existingEmploye.setMotDePasse(updatedEmploye.getMotDePasse());
	        long idC=updatedEmploye.getCentre();
			Optional<Centre> optionalCentre = CenterRepository.findById(idC);
		    if (optionalCentre.isPresent()) {
		    	existingEmploye.setCentre(optionalCentre.get());
		    }
	        Employe savedEmploye = employeRepository.save(existingEmploye);
	        return new ResponseEntity<>(savedEmploye, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/employe/{id}")
	public ResponseEntity<Void> deleteEmploye(@PathVariable("id") Long id) {
	    Optional<Employe> optionalEmploye = employeRepository.findById(id);
	    if (optionalEmploye.isPresent()) {
	    	employeRepository.delete(optionalEmploye.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
