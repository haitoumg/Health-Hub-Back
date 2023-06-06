
package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Diagnostic;

@Repository
public interface DiagnosticRepository extends JpaRepository <Diagnostic,Long>{
}
