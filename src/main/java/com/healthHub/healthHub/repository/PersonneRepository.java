package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthHub.healthHub.model.Personne;

public interface PersonneRepository extends JpaRepository <Personne,Long>{
}