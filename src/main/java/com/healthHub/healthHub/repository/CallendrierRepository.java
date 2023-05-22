package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Calendrier;

@Repository
public interface CallendrierRepository extends JpaRepository <Calendrier,Long>{
}
