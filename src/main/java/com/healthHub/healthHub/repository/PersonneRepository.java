package com.healthHub.healthHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
	Optional<Personne> findByemailAndPassword(String email, String password);

	Optional<Personne> findByemail(String email);
}