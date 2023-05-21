package com.healthHub.healthHub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.model.Centre;
import com.healthHub.healthHub.repository.CentreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class CentreController {

	private final CentreRepository centreRepository;

	@Autowired
	public CentreController(CentreRepository centreRepository) {
		this.centreRepository = centreRepository;
	}

	@PostMapping("/centre")
	public ResponseEntity<Centre> createCentre(@RequestBody Centre centre) {
		Centre createdCentre = centreRepository.save(centre);
		return new ResponseEntity<>(createdCentre, HttpStatus.CREATED);
	}
	
	@GetMapping("/centres")
	public ResponseEntity<List<Centre>> getAllCentres() {
	    List<Centre> centres = centreRepository.findAll();
	    return new ResponseEntity<>(centres, HttpStatus.OK);
	}
	
	@GetMapping("/centre/{id}")
	public ResponseEntity<Centre> getCentreById(@PathVariable("id") Long id) {
		Optional<Centre> centre = centreRepository.findById(id);
		if (centre.isPresent()) {
			return new ResponseEntity<>(centre.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/centre/{id}")
	public ResponseEntity<Centre> updateCentre(@PathVariable("id") Long id, @RequestBody Centre updatedCentre) {
	    Optional<Centre> optionalCentre = centreRepository.findById(id);
	    if (optionalCentre.isPresent()) {
	    	Centre existingCentre = optionalCentre.get();
	        existingCentre.setCentreName(updatedCentre.getCentreName());
	        existingCentre.setVille(updatedCentre.getVille());
	        existingCentre.setAdresse(updatedCentre.getAdresse());
	        Centre savedCentre = centreRepository.save(existingCentre);
	        return new ResponseEntity<>(savedCentre, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/centre/{id}")
	public ResponseEntity<Void> deleteCentre(@PathVariable("id") Long id) {
	    Optional<Centre> optionalCentre = centreRepository.findById(id);
	    if (optionalCentre.isPresent()) {
	    	centreRepository.delete(optionalCentre.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}