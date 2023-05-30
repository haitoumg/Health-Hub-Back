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
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.model.Personne;
import com.healthHub.healthHub.repository.PersonneRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class PersonneController {

	private final PersonneRepository personneRepository;

	@Autowired
	public PersonneController(PersonneRepository personneRepository) {
		this.personneRepository = personneRepository;
	}
	
	/* The class Personne is an abstract class, so we can't create instances of the class
	 * @PostMapping("/personne") public ResponseEntity<Personne>
	 * createPersonne(@RequestBody Personne personne) { Personne createdPersonne =
	 * personneRepository.save(personne); return new
	 * ResponseEntity<>(createdPersonne, HttpStatus.CREATED); }
	 */
	
	@GetMapping("/personns")
	public ResponseEntity<List<Personne>> getAllPersonnes() {
	    List<Personne> personnes = personneRepository.findAll();
	    return new ResponseEntity<>(personnes, HttpStatus.OK);
	}
	@GetMapping("/personne/{id}")
	public ResponseEntity<Personne> getPersonneById(@PathVariable("id") Long id) {
		Optional<Personne> personne = personneRepository.findById(id);
		if (personne.isPresent()) {
			return new ResponseEntity<>(personne.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/personne/{id}")
	public ResponseEntity<Personne> updatePersonne(@PathVariable("id") Long id, @RequestBody Personne updatedPersonne) {
	    Optional<Personne> optionalPersonne = personneRepository.findById(id);
	    if (optionalPersonne.isPresent()) {
	    	Personne existingPersonne = optionalPersonne.get();
	        existingPersonne.setlastName(updatedPersonne.getlastName()); 
	        existingPersonne.setfirstName(updatedPersonne.getfirstName()); 
	        existingPersonne.setBirthDate(updatedPersonne.getBirthDate());
	        existingPersonne.setTelephone(updatedPersonne.getTelephone());
	        existingPersonne.setEmail(updatedPersonne.getEmail());
	        existingPersonne.setPassword(updatedPersonne.getPassword());
	        existingPersonne.setRole(updatedPersonne.getRole());
	        existingPersonne.setHub(updatedPersonne.getHub());
	        Personne savedPersonne = personneRepository.save(existingPersonne);
	        return new ResponseEntity<>(savedPersonne, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/personne/{id}")
	public ResponseEntity<Void> deletePersonne(@PathVariable("id") Long id) {
	    Optional<Personne> optionalPersonne = personneRepository.findById(id);
	    if (optionalPersonne.isPresent()) {
	    	personneRepository.delete(optionalPersonne.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
}
