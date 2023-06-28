package com.healthHub.healthHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthHub.healthHub.model.Calendar;

import java.util.List;

@Repository
public interface CalenderRepository extends JpaRepository <Calendar,Long>{
    List<Calendar> findByDoctorRoleAndDoctorHubHubName(String userRole, String hub);
    List<Calendar> findByDoctorHubCity(String city);
    List<Calendar> findAllByDoctorPersonneId(int personneId);
}
