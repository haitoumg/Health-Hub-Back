package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository <Appointment,Long>{
}
