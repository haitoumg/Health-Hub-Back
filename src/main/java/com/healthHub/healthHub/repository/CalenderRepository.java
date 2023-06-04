package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Calendar;

@Repository
public interface CalenderRepository extends JpaRepository <Calendar,Long>{
}
