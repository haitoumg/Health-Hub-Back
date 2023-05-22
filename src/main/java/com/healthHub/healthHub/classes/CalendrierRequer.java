package com.healthHub.healthHub.classes;

import java.sql.Time;
import java.util.Date;

public class CalendrierRequer {
	private Date jourTravail;
	private Time heureDebut;
	private Time heureFin;
	private long docteurId;

	public long getDocteurId() {
		return docteurId;
	}
	public void setDocteurId(long docteurId) {
		this.docteurId = docteurId;
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
	public CalendrierRequer() {

	}
	public CalendrierRequer(Date jourTravail, Time heureDebut, Time heureFin, long docteurId) {
		super();
		this.jourTravail = jourTravail;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.docteurId = docteurId;
	}
}
