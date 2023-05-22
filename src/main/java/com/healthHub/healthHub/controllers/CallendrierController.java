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

import com.healthHub.healthHub.classes.CalendrierRequer;
import com.healthHub.healthHub.classes.DocteurRequer;
import com.healthHub.healthHub.model.Calendrier;
import com.healthHub.healthHub.model.Centre;
import com.healthHub.healthHub.model.Docteur;
import com.healthHub.healthHub.repository.CallendrierRepository;
import com.healthHub.healthHub.repository.CentreRepository;
import com.healthHub.healthHub.repository.DocteurRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class CallendrierController {
	private final CallendrierRepository callendrierRepository;
	private final DocteurRepository docteurRepository;

	@Autowired
	public CallendrierController(CallendrierRepository callendrierRepository,DocteurRepository docteurRepository) {
		this.docteurRepository = docteurRepository;
		this.callendrierRepository = callendrierRepository;
	}


	@PostMapping("/calendrier")
	public ResponseEntity<Calendrier> createCalendrier(@RequestBody CalendrierRequer calendrierRequ) {
		Calendrier calendrier=new Calendrier();
		calendrier.setHeureDebut(calendrierRequ.getHeureDebut());
		calendrier.setHeureFin(calendrierRequ.getHeureFin());
		calendrier.setJourTravail(calendrierRequ.getJourTravail());

		long id=calendrierRequ.getDocteurId();
		Optional<Docteur> optionalCentre = docteurRepository.findById(id);
	    if (optionalCentre.isPresent()) {
	    	calendrier.setDocteur((optionalCentre.get()));
	    }
	    Calendrier createdCalendrier = callendrierRepository.save(calendrier);
		return new ResponseEntity<>(createdCalendrier, HttpStatus.CREATED);
	}
	
	@GetMapping("/calendriers")
	public ResponseEntity<List<Calendrier>> getAllCalendriers() {
	    List<Calendrier> calendriers = callendrierRepository.findAll();
	    return new ResponseEntity<>(calendriers, HttpStatus.OK);
	}
	
	@GetMapping("/calendrier/{id}")
	public ResponseEntity<Calendrier> getCalendrierById(@PathVariable("id") Long id) {
		Optional<Calendrier> calendrier = callendrierRepository.findById(id);
		if (calendrier.isPresent()) {
			return new ResponseEntity<>(calendrier.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/calendrier/{id}")
	public ResponseEntity<Calendrier> updateDCalendrier(@PathVariable("id") Long id, @RequestBody CalendrierRequer updatedDocteur) {
	    Optional<Calendrier> optionalCalendrier = callendrierRepository.findById(id);
	    if (optionalCalendrier.isPresent()) {
	    	Calendrier existingCalendrier = optionalCalendrier.get();
	    	existingCalendrier.setHeureDebut(updatedDocteur.getHeureDebut());
	    	existingCalendrier.setHeureFin(updatedDocteur.getHeureFin());
	    	existingCalendrier.setJourTravail(updatedDocteur.getJourTravail());

			long idD=updatedDocteur.getDocteurId();
			Optional<Docteur> optionalCentre = docteurRepository.findById(idD);
		    if (optionalCentre.isPresent()) {
		    	existingCalendrier.setDocteur((optionalCentre.get()));
		    }

		    Calendrier savedDocteur = callendrierRepository.save(existingCalendrier);
	        return new ResponseEntity<>(savedDocteur, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/calendrier/{id}")
	public ResponseEntity<Void> deleteCalendrier(@PathVariable("id") Long id) {
	    Optional<Calendrier> optionalDocteur = callendrierRepository.findById(id);
	    if (optionalDocteur.isPresent()) {
	    	callendrierRepository.delete(optionalDocteur.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}

