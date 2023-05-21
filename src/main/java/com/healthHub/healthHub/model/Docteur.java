package com.healthHub.healthHub.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("D")
public class Docteur extends Personne{
	
	private int numDocteur;
	
	@Column(nullable = false, length = 50)
	private String specialité;

	public int getNumDocteur() {
		return numDocteur;
	}

	public void setNumDocteur(int numDocteur) {
		this.numDocteur = numDocteur;
	}

	public String getSpecialité() {
		return specialité;
	}

	public void setSpecialité(String specialité) {
		this.specialité = specialité;
	}

	public Docteur() {
		super();
	}

	public Docteur(int numDocteur, String specialité) {
		super();
		this.numDocteur = numDocteur;
		this.specialité = specialité;
	}
	
	
	
}
