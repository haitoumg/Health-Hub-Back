package com.healthHub.healthHub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("E")
public class Employee extends Personne {

	private int numEmployee;

	public int getnumEmployee() {
		return numEmployee;
	}

	public void setnumEmployee(int numEmployee) {
		this.numEmployee = numEmployee;
	}

	public Employee() {
		super();
	}

	public Employee(int numEmployee) {
		super();
		this.numEmployee = numEmployee;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Appointment> appointment;

}