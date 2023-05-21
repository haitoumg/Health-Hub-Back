package com.healthHub.healthHub.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@DiscriminatorValue("E")
public class Employe extends Personne{
	
	private int numEmploye;

	public int getNumEmploye() {
		return numEmploye;
	}

	public void setNumEmploye(int numEmploye) {
		this.numEmploye = numEmploye;
	}

	public Employe() {
		super();
	}

	public Employe(int numEmploye) {
		super();
		this.numEmploye = numEmploye;
	}
	
	
}
