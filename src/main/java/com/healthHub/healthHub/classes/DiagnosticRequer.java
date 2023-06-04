package com.healthHub.healthHub.classes;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DiagnosticRequer {

	private String note;
	
	private long employeeId;

	private long doctorId;

	public DiagnosticRequer(String note, long employeeId, long doctorId) {
		super();
		this.note = note;
		this.employeeId = employeeId;
		this.doctorId = doctorId;
	}

	public DiagnosticRequer() {
		super();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}


}
