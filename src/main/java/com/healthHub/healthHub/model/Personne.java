package com.healthHub.healthHub.model;

import java.util.Date;

import jakarta.persistence.FetchType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personneId;
	
	@Column(nullable = false, length = 50)
	private String nom;
	
	@Column(nullable = false, length = 50)
	private String prenom;

	@Column(nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;	
    
    @Column(nullable = false, length = 50)
    private String telephone;
    
    @Column(nullable = false, length = 50)
    private String email;
    
    @Column(nullable = false, length = 50)
    private String motDePasse;
    
	@Column(nullable = false, columnDefinition = "ENUM('Docteur', 'Employe','Admin')")
	private String role;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centreId")
    private Centre centre;


	public int getPersonneId() {
		return personneId;
	}

	public void setPersonneId(int personneId) {
		this.personneId = personneId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}
	
	public Personne() {
		super();
	}

	public Personne(int personneId, String nom, String prenom, Date dateNaissance, String telephone, String email,
			String motDePasse, String role, Centre centre) {
		super();
		this.personneId = personneId;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
		this.centre = centre;
	}
	
}
