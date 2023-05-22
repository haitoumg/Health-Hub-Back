package com.healthHub.healthHub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;



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
	
	@OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<RendezVous> rendezVous;
	
}
