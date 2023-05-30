package com.healthHub.healthHub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("D")
public class Doctor extends Personne {

	private int numDoctor;

	@Column(nullable = false, length = 50)
	private String specialty;

	public int getNumDoctor() {
		return numDoctor;
	}

	public void setNumDoctor(int numDoctor) {
		this.numDoctor = numDoctor;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Doctor() {
		super();
	}

	public Doctor(int numDoctor, String specialty) {
		super();
		this.numDoctor = numDoctor;
		this.specialty = specialty;
	}

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Calendar> Calendars;

}
