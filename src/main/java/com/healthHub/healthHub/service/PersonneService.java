package com.healthHub.healthHub.service;

public class PersonneService {
}
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthHub.healthHub.model.Personne;
import com.healthHub.healthHub.repository.PersonneRepository;

@Service
public class PersonneService {
	
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PersonneRepository personneRepository;
	
	public Personne savePersonne(Personne personne) {
    personne.setPassword(passwordEncoder.encode(personne.getPassword()));
			
	return	personneRepository.save(personne)	;		
	
	}
}
*/

