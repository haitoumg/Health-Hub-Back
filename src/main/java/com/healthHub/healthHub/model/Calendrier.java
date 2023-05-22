package com.healthHub.healthHub.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Calendrier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CalendrierId;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jourTravail;
	
	@Column(nullable = false)
	private Time heureDebut;

	@Column(nullable = false)
	private Time heureFin;
	
	public Calendrier() {
		
	}
	



	public int getCalendrierId() {
		return CalendrierId;
	}


	public void setCalendrierId(int calendrierId) {
		CalendrierId = calendrierId;
	}


	public Date getJourTravail() {
		return jourTravail;
	}


	public void setJourTravail(Date jourTravail) {
		this.jourTravail = jourTravail;
	}


	public Time getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}


	public Time getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}


	public Docteur getDocteur() {
		return docteur;
	}


	public Calendrier(int calendrierId, Date jourTravail, Time heureDebut, Time heureFin, Docteur docteur,
			List<RendezVous> rendezVous) {
		super();
		CalendrierId = calendrierId;
		this.jourTravail = jourTravail;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.docteur = docteur;
		this.rendezVous = rendezVous;
	}




	public void setDocteur(Docteur docteur) {
		this.docteur = docteur;
	}



	
	public List<RendezVous> getRendezVous() {
		return rendezVous;
	}




	public void setRendezVous(List<RendezVous> rendezVous) {
		this.rendezVous = rendezVous;
	}




	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personneId")
    private Docteur docteur;
	
	@OneToMany(mappedBy = "calendrier")
	@JsonIgnore
    private List<RendezVous> rendezVous;
}
