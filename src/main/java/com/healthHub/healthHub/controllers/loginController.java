package com.healthHub.healthHub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.classes.ErrorResponse;
import com.healthHub.healthHub.classes.loginRequer;
import com.healthHub.healthHub.classes.personneLogin;
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
	public ResponseEntity<?> getPersonneBylogin(@RequestBody loginRequer login) {
		Optional<Personne>  personne = personneRepository.findByemailAndMotDePasse(login.getEmail(),login.getMotdepass());
		
		if (personne.isPresent()) {
			personneLogin p=new personneLogin(personne.get().getNom(),personne.get().getPrenom(),personne.get().getDateNaissance(),personne.get().getTelephone(),personne.get().getEmail(),personne.get().getCentre().getCentreId());
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			String errorMessage = "Incorrect Email or Password";
	        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/changepassword")
	public ResponseEntity<Personne> changePassword(@RequestBody loginRequer login) {
		Optional<Personne> personne = personneRepository.findByemail(login.getEmail());
		if (personne.isPresent()) {
			Personne existingPersonne =personne.get();
			existingPersonne.setMotDePasse(login.getMotdepass());
			Personne savedPersonne =personneRepository.save(existingPersonne);
			return new ResponseEntity<>(savedPersonne, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
}
