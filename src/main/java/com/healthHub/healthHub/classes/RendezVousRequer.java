package com.healthHub.healthHub.classes;

import java.util.Date;

public class RendezVousRequer {


	private Date dateRendezVous;


	private long employeId;

	private long calendrierId;

	public RendezVousRequer() {
		super();
	}

	public RendezVousRequer( Date dateRendezVous, long employeId, long calendrierId) {
		super();

		this.dateRendezVous = dateRendezVous;
		this.employeId = employeId;
		this.calendrierId = calendrierId;
	}



	public Date getDateRendezVous() {
		return dateRendezVous;
	}

	public void setDateRendezVous(Date dateRendezVous) {
		this.dateRendezVous = dateRendezVous;
	}


	public long getEmployeId() {
		return employeId;
	}

	public void setEmployeId(long employeId) {
		this.employeId = employeId;
	}

	public long getCalendrierId() {
		return calendrierId;
	}

	public void setCalendrierId(long calendrierId) {
		this.calendrierId = calendrierId;
	}

}
