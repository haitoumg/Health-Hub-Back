package com.healthHub.healthHub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Centre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int centreId;
	private String centreName;

	@Column(nullable = false, columnDefinition = "ENUM('Tetouan', 'Casablanca')")
	private String ville;

	private String adresse;
	
	
	public Centre() {
		super();
	}

	public Centre(int centreId, String centreName, String ville, String adresse) {
		this.centreId = centreId;
		this.centreName = centreName;
		this.ville = ville;
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCentreId() {
		return centreId;
	}

	public void setCentreId(int hubId) {
		this.centreId = hubId;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String hubName) {
		this.centreName = hubName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;

	}

}
