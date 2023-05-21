package com.healthHub.healthHub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Centre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int centreId;
	
	@Column(nullable = false)
	private String centreName;

	@Column(nullable = false)
	private String ville;
	
	@Column(nullable = false)
	private String adresse;
	
	@OneToMany(mappedBy = "centre")
	@JsonIgnore
    private List<Personne> personnes;

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
	
	
	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public Centre() {
		super();
	}
	public Centre(int centreId, String centreName, String ville, String adresse, List<Personne> personnes) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.ville = ville;
		this.adresse = adresse;
		this.personnes = personnes;
	}
	
}
