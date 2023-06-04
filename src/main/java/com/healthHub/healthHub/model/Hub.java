package com.healthHub.healthHub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hubId;

	@Column(nullable = false)
	private String hubName;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String address;

	@OneToMany(mappedBy = "hub", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Personne> persons;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getHubId() {
		return hubId;
	}

	public void setHubId(int hubId) {
		this.hubId = hubId;
	}

	public String getHubName() {
		return hubName;
	}

	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Personne> getPersons() {
		return persons;
	}

	public void setPersons(List<Personne> persons) {
		this.persons = persons;
	}

	public Hub() {
		super();
	}

	public Hub(int hubId, String hubName, String city, String address, List<Personne> persons) {
		super();
		this.hubId = hubId;
		this.hubName = hubName;
		this.city = city;
		this.address = address;
		this.persons = persons;
	}
}