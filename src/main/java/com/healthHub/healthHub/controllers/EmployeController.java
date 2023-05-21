package com.healthHub.healthHub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.model.Employe;
import com.healthHub.healthHub.repository.EmployeRepository;


@RestController
public class EmployeController {
	private final EmployeRepository employeRepository;

	@Autowired
	public EmployeController(EmployeRepository employeRepository) {
		this.employeRepository = employeRepository;
	}

	@PostMapping("/employe")
	public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
		Employe createdEmploye = employeRepository.save(employe);
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
	public ResponseEntity<Employe> updateEmploye(@PathVariable("id") Long id, @RequestBody Employe updatedEmploye) {
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
	        existingEmploye.setRole(updatedEmploye.getRole());
	        existingEmploye.setCentre(updatedEmploye.getCentre());
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
