package com.healthHub.healthHub.classes;

import java.util.Date;


public class PersonneRequer {

	private String nom;
	

	private String prenom;


    private Date dateNaissance;	
    

    private String telephone;
    

    private String email;
    

    private String motDePasse;
    

	private long idCentre;


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

	
	public long getCentre() {
		return idCentre;
	}

	public void setCentre(long idCentre) {
		this.idCentre = idCentre;
	}
	
	public PersonneRequer() {
		super();
	}

	public PersonneRequer(String nom, String prenom, Date dateNaissance, String telephone, String email,
			String motDePasse,  long idCentre) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.email = email;
		this.motDePasse = motDePasse;
		this.idCentre = idCentre;
	}
	
}
