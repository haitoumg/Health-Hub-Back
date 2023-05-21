package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Employe;

@Repository
public interface EmployeRepository extends JpaRepository <Employe,Long>{
}
