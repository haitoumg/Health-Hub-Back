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

import com.healthHub.healthHub.model.Docteur;
import com.healthHub.healthHub.repository.DocteurRepository;

@RestController
public class DocteurController {
	private final DocteurRepository DocteurRepository;

	@Autowired
	public DocteurController(DocteurRepository DocteurRepository) {
		this.DocteurRepository = DocteurRepository;
	}

	@PostMapping("/docteur")
	public ResponseEntity<Docteur> createDocteur(@RequestBody Docteur Docteur) {
		Docteur createdDocteur = DocteurRepository.save(Docteur);
		return new ResponseEntity<>(createdDocteur, HttpStatus.CREATED);
	}
	
	@GetMapping("/docteurs")
	public ResponseEntity<List<Docteur>> getAllDocteurs() {
	    List<Docteur> Docteurs = DocteurRepository.findAll();
	    return new ResponseEntity<>(Docteurs, HttpStatus.OK);
	}
	
	@GetMapping("/docteur/{id}")
	public ResponseEntity<Docteur> getDocteurById(@PathVariable("id") Long id) {
		Optional<Docteur> Docteur = DocteurRepository.findById(id);
		if (Docteur.isPresent()) {
			return new ResponseEntity<>(Docteur.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/docteur/{id}")
	public ResponseEntity<Docteur> updateDocteur(@PathVariable("id") Long id, @RequestBody Docteur updatedDocteur) {
	    Optional<Docteur> optionalDocteur = DocteurRepository.findById(id);
	    if (optionalDocteur.isPresent()) {
	    	Docteur existingDocteur = optionalDocteur.get();
	    	existingDocteur.setSpecialité(updatedDocteur.getSpecialité());
	        existingDocteur.setNom(updatedDocteur.getNom());
	        existingDocteur.setPrenom(updatedDocteur.getPrenom());
	        existingDocteur.setDateNaissance(updatedDocteur.getDateNaissance());
	        existingDocteur.setTelephone(updatedDocteur.getTelephone());
	        existingDocteur.setEmail(updatedDocteur.getEmail());
	        existingDocteur.setMotDePasse(updatedDocteur.getMotDePasse());
	        existingDocteur.setRole(updatedDocteur.getRole());
	        existingDocteur.setCentre(updatedDocteur.getCentre());
	        Docteur savedDocteur = DocteurRepository.save(existingDocteur);
	        return new ResponseEntity<>(savedDocteur, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/docteur/{id}")
	public ResponseEntity<Void> deleteDocteur(@PathVariable("id") Long id) {
	    Optional<Docteur> optionalDocteur = DocteurRepository.findById(id);
	    if (optionalDocteur.isPresent()) {
	    	DocteurRepository.delete(optionalDocteur.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
