
package com.healthHub.healthHub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Diagnostic;
import com.healthHub.healthHub.model.Doctor;
import com.healthHub.healthHub.model.Employee;


@Repository
public interface DiagnosticRepository extends JpaRepository <Diagnostic,Long>{
	Optional<List<Diagnostic>> findAllBydoctor(Doctor d);
	Optional<List<Diagnostic>> findAllByemployeeAndDoctor(Employee e,Doctor d);
}
