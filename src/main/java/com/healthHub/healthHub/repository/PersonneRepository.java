package com.healthHub.healthHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthHub.healthHub.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository <Personne,Long>{
	Optional<Personne> findByemailAndMotDePasse(String email,String motDePasse);
	Optional<Personne> findByemail(String email);
}