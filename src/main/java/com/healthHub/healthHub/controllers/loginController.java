package com.healthHub.healthHub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.model.Docteur;
import com.healthHub.healthHub.model.Personne;
import com.healthHub.healthHub.repository.PersonneRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class loginController {

	private final PersonneRepository personneRepository;

	@Autowired
	public loginController(PersonneRepository personneRepository) {
		this.personneRepository = personneRepository;
	}

	@GetMapping("/login")
	public ResponseEntity<Personne> getPersonneBylogin(@RequestParam String email,@RequestParam String motdepass) {
		Optional<Personne> personne = personneRepository.findByemailAndMotDePasse(email,motdepass);
		if (personne.isPresent()) {
			return new ResponseEntity<>(personne.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/changepassword")
	public ResponseEntity<Personne> changePassword(@RequestParam String email,@RequestParam String motdepass) {
		Optional<Personne> personne = personneRepository.findByemail(email);
		if (personne.isPresent()) {
			Personne existingPersonne =personne.get();
			existingPersonne.setMotDePasse(motdepass);
			return new ResponseEntity<>(personne.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
}
