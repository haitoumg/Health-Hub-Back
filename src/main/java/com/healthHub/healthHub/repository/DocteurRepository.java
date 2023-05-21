package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Docteur;

@Repository
public interface DocteurRepository extends JpaRepository <Docteur,Long>{
}
