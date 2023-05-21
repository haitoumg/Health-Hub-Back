package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository <Personne,Long>{
}