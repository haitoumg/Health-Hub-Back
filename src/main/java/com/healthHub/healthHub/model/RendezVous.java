package com.healthHub.healthHub.model;

import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RendezVous {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int RendezVousId;

	@Column(nullable = false)
	private boolean accept;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateRendezVous;

	@Column(nullable = false)
	private boolean Annule;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personneId")
    private Employe employe;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CalendrierId")
    private Calendrier calendrier;

	public RendezVous() {

	}

	
	public int getRendezVousId() {
		return RendezVousId;
	}

	public RendezVous(int rendezVousId, boolean accept, Date dateRendezVous, boolean annule, Employe employe,
			Calendrier calendrier) {
		super();
		RendezVousId = rendezVousId;
		this.accept = accept;
		this.dateRendezVous = dateRendezVous;
		Annule = annule;
		this.employe = employe;
		this.calendrier = calendrier;
	}


	public Employe getEmploye() {
		return employe;
	}


	public void setEmploye(Employe employe) {
		this.employe = employe;
	}


	public Calendrier getCalendrier() {
		return calendrier;
	}


	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}


	public void setRendezVousId(int rendezVousId) {
		RendezVousId = rendezVousId;
	}

	public boolean isAccept() {
		return accept;
	}




	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public Date getDateRendezVous() {
		return dateRendezVous;
	}

	public void setDateRendezVous(Date dateRendezVous) {
		this.dateRendezVous = dateRendezVous;
	}

	public boolean isAnnule() {
		return Annule;
	}

	public void setAnnule(boolean annule) {
		Annule = annule;
	}


}
