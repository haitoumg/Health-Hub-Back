package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Appointment;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository <Appointment,Long>{
     List<Appointment> findAllByCancelledAndCalendarDoctorPersonneId(boolean cancelled,int personneId);

     List<Appointment> findAllByCalendarDoctorHubHubNameAndCancelled(String whatHubReservation, Boolean isCancelled);

     Optional<Appointment> findByCalendarCalendarId(int calendarId);
}
