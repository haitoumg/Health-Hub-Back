package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Long>{
}
