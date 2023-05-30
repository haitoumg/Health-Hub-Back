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

import com.healthHub.healthHub.model.Hub;
import com.healthHub.healthHub.repository.HubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class HubController {

	private final HubRepository hubRepository;

	@Autowired
	public HubController(HubRepository hubRepository) {
		this.hubRepository = hubRepository;
	}

	@PostMapping("/hub")
	public ResponseEntity<Hub> createHub(@RequestBody Hub Hub) {
		Hub createdHub = hubRepository.save(Hub);
		return new ResponseEntity<>(createdHub, HttpStatus.CREATED);
	}
	
	@GetMapping("/hubs")
	public ResponseEntity<List<Hub>> getAllHubs() {
	    List<Hub> Hubs = hubRepository.findAll();
	    return new ResponseEntity<>(Hubs, HttpStatus.OK);
	}
	
	@GetMapping("/hub/{id}")
	public ResponseEntity<Hub> getHubById(@PathVariable("id") Long id) {
		Optional<Hub> Hub = hubRepository.findById(id);
		if (Hub.isPresent()) {
			return new ResponseEntity<>(Hub.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/hub/{id}")
	public ResponseEntity<Hub> updateHub(@PathVariable("id") Long id, @RequestBody Hub updatedHub) {
	    Optional<Hub> optionalHub = hubRepository.findById(id);
	    if (optionalHub.isPresent()) {
	    	Hub existingHub = optionalHub.get();
	        existingHub.setHubName(updatedHub.getHubName());
	        existingHub.setCity(updatedHub.getCity());
	        existingHub.setAddress(updatedHub.getAddress());
	        Hub savedHub = hubRepository.save(existingHub);
	        return new ResponseEntity<>(savedHub, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/hub/{id}")
	public ResponseEntity<Void> deleteHub(@PathVariable("id") Long id) {
	    Optional<Hub> optionalHub = hubRepository.findById(id);
	    if (optionalHub.isPresent()) {
	    	hubRepository.delete(optionalHub.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}