package com.healthHub.healthHub.model;

import java.util.Date;

import jakarta.persistence.FetchType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personneId;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@Column(nullable = false, length = 50)
	private String telephone;

	@Column(nullable = false, length = 50, unique = true)
	private String email;

    
	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, columnDefinition = "ENUM('Doctor', 'Employee','Admin')")
	private String role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hubId")
	private Hub hub;

	public int getPersonneId() {
		return personneId;
	}

	public void setPersonneId(int personneId) {
		this.personneId = personneId;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Hub getHub() {
		return hub;
	}

	public void setHub(Hub hub) {
		this.hub = hub;
	}

	public Personne() {
		super();
	}

	public Personne(int personneId, String lastName, String firstName, Date birthDate, String telephone, String email,
			String password, String role, Hub hub) {
		super();
		this.personneId = personneId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.role = role;
		this.hub = hub;
	}
}