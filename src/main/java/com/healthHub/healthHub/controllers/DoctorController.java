package com.healthHub.healthHub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.healthHub.healthHub.classes.PersoneInfos;
import com.healthHub.healthHub.model.Employee;
import com.healthHub.healthHub.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.classes.DoctorRequer;
import com.healthHub.healthHub.model.Hub;
import com.healthHub.healthHub.model.Doctor;
import com.healthHub.healthHub.repository.HubRepository;
import com.healthHub.healthHub.repository.DoctorRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class DoctorController {
	private final DoctorRepository doctorRepository;
	private final HubRepository hubRepository;

	@Autowired
	public DoctorController(DoctorRepository doctorRepository,HubRepository hubRepository) {
		this.hubRepository = hubRepository;
		this.doctorRepository = doctorRepository;
	}


	@PostMapping("/doctor")
	public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorRequer doctorRequ) {
		Doctor doctor=new Doctor(doctorRequ.getNumDoctor(), doctorRequ.getSpecialty());

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryPwd= bcrypt.encode(doctorRequ.getPassword());
		doctor.setEmail(doctorRequ.getEmail());
		doctor.setBirthDate(doctorRequ.getBirthDate());
		doctor.setPassword(encryPwd);
		doctor.setlastName(doctorRequ.getlastName());
		doctor.setfirstName(doctorRequ.getfirstName());
		doctor.setRole("Doctor");
		doctor.setTelephone(doctorRequ.getTelephone());
		
		long id=doctorRequ.getIdHub();
		Optional<Hub> optionalHub = hubRepository.findById(id);
	    if (optionalHub.isPresent()) {
	    	doctor.setHub(optionalHub.get());
	    }
		Doctor createdDoctor = doctorRepository.save(doctor);
		return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
	}
	
	@GetMapping("/doctors")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
	    List<Doctor> doctors = doctorRepository.findAll();
	    return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	@GetMapping("/doctorsInfos")
	public ResponseEntity<List<PersoneInfos>> getAllDoctorsInfos() {
		List<Doctor> doctors = doctorRepository.findAll();
		List<PersoneInfos> personesInfos=new ArrayList<>();
		for (int i = 0; i < doctors.size(); i++) {
			Personne persone = doctors.get(i);
			PersoneInfos personeInfos1=new PersoneInfos(persone.getPersonneId(), persone.getfirstName()+" "+persone.getlastName());
			personesInfos.add(personeInfos1);
		}
		return new ResponseEntity<>(personesInfos, HttpStatus.OK);
	}
	@GetMapping("/doctor/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		if (doctor.isPresent()) {
			return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/doctor/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorRequer updatedDoctor) {
	    Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
	    if (optionalDoctor.isPresent()) {
	    	Doctor existingDoctor = optionalDoctor.get();
	    	existingDoctor.setSpecialty(updatedDoctor.getSpecialty());
	        existingDoctor.setlastName(updatedDoctor.getlastName());
	        existingDoctor.setfirstName(updatedDoctor.getfirstName());
	        existingDoctor.setBirthDate(updatedDoctor.getBirthDate());
	        existingDoctor.setTelephone(updatedDoctor.getTelephone());
	        existingDoctor.setEmail(updatedDoctor.getEmail());
	        existingDoctor.setPassword(updatedDoctor.getPassword());
	        long idH=updatedDoctor.getIdHub();
			Optional<Hub> optionalHub = hubRepository.findById(idH);
		    if (optionalHub.isPresent()) {
		    	existingDoctor.setHub(optionalHub.get());
		    }
	        Doctor savedDoctor = doctorRepository.save(existingDoctor);
	        return new ResponseEntity<>(savedDoctor, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/doctor/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable("id") Long id) {
	    Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
	    if (optionalDoctor.isPresent()) {
	    	doctorRepository.delete(optionalDoctor.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
