package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.RendezVous;

@Repository
public interface RendezVousRepository extends JpaRepository <RendezVous,Long>{
}
