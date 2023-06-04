package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor,Long>{
}
