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
import com.healthHub.healthHub.classes.RendezVousRequer;
import com.healthHub.healthHub.model.Calendrier;
import com.healthHub.healthHub.model.Employe;
import com.healthHub.healthHub.model.RendezVous;
import com.healthHub.healthHub.repository.CallendrierRepository;
import com.healthHub.healthHub.repository.EmployeRepository;
import com.healthHub.healthHub.repository.RendezVousRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class RendezVousController {
	private final CallendrierRepository callendrierRepository;
	private final EmployeRepository EmployeRepository;
	private final RendezVousRepository RendezvousRepository;

	@Autowired
	public RendezVousController(CallendrierRepository callendrierRepository,EmployeRepository EmployeRepository,RendezVousRepository RendezvousRepository) {
		this.EmployeRepository = EmployeRepository;
		this.callendrierRepository = callendrierRepository;
		this.RendezvousRepository = RendezvousRepository;
	}


	@PostMapping("/rendezvous")
	public ResponseEntity<RendezVous> createCalendrier(@RequestBody RendezVousRequer rvRequ) {
		RendezVous rv=new RendezVous();
		rv.setDateRendezVous(rvRequ.getDateRendezVous());
		rv.setAnnule(false);
		rv.setAccept(false);
		long idE=rvRequ.getEmployeId();
		long idC=rvRequ.getCalendrierId();
		Optional<Employe> optionalE = EmployeRepository.findById(idE);
	    if (optionalE.isPresent()) {
	    	rv.setEmploye((optionalE.get()));
	    }
	    Optional<Calendrier> optionalC = callendrierRepository.findById(idC);
	    if (optionalC.isPresent()) {
	    	rv.setCalendrier((optionalC.get()));
	    }
	    RendezVous createdrv = RendezvousRepository.save(rv);
		return new ResponseEntity<>(createdrv, HttpStatus.CREATED);
	}
	
	@GetMapping("/rendezvous")
	public ResponseEntity<List<RendezVous>> getAllCalendriers() {
	    List<RendezVous> calendriers = RendezvousRepository.findAll();
	    return new ResponseEntity<>(calendriers, HttpStatus.OK);
	}
	
	@GetMapping("/rendezvous/{id}")
	public ResponseEntity<RendezVous> getCalendrierById(@PathVariable("id") Long id) {
		Optional<RendezVous> rv = RendezvousRepository.findById(id);
		if (rv.isPresent()) {
			return new ResponseEntity<>(rv.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/rendezvous/{id}")
	public ResponseEntity<RendezVous> updateDCalendrier(@PathVariable("id") Long id, @RequestBody RendezVousRequer rvRequ) {
	    Optional<RendezVous> optionalrv = RendezvousRepository.findById(id);
	    if (optionalrv.isPresent()) {
	    	RendezVous existingrv = optionalrv.get();
	    	
	    	existingrv.setDateRendezVous(rvRequ.getDateRendezVous());
	    	existingrv.setAnnule(false);
	    	existingrv.setAccept(false);
			long idE=rvRequ.getEmployeId();
			long idC=rvRequ.getCalendrierId();
			Optional<Employe> optionalE = EmployeRepository.findById(idE);
		    if (optionalE.isPresent()) {
		    	existingrv.setEmploye((optionalE.get()));
		    }
		    Optional<Calendrier> optionalC = callendrierRepository.findById(idC);
		    if (optionalC.isPresent()) {
		    	existingrv.setCalendrier((optionalC.get()));
		    }
		    RendezVous savedEmploye = RendezvousRepository.save(existingrv);
	        return new ResponseEntity<>(savedEmploye, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/rendezvous/{id}")
	public ResponseEntity<Void> deleteCalendrier(@PathVariable("id") Long id) {
	    Optional<RendezVous> optionalEmploye = RendezvousRepository.findById(id);
	    if (optionalEmploye.isPresent()) {
	    	RendezvousRepository.delete(optionalEmploye.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}

