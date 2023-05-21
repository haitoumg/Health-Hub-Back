package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Centre;

@Repository
public interface CentreRepository extends JpaRepository <Centre,Long>{
}
