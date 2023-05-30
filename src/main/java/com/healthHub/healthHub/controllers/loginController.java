package com.healthHub.healthHub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.classes.ErrorResponse;
import com.healthHub.healthHub.classes.loginRequer;
import com.healthHub.healthHub.classes.personneLogin;
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
		Optional<Personne>  personne = personneRepository.findByemailAndPassword(login.getEmail(),login.getPassword());
		
		if (personne.isPresent()) {
			personneLogin p=new personneLogin(personne.get().getlastName(),personne.get().getfirstName(),personne.get().getBirthDate(),personne.get().getTelephone(),personne.get().getEmail(),personne.get().getHub().getHubId());
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
			existingPersonne.setPassword(login.getPassword());
			Personne savedPersonne =personneRepository.save(existingPersonne);
			return new ResponseEntity<>(savedPersonne, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
}