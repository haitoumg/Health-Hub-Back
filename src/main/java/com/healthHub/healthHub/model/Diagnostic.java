package com.healthHub.healthHub.model;



import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Diagnostic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int DiagnosticId;

	@Column(nullable = false)
	private String note;
	
	@Column(nullable = false)
	private Date DiagnosticDate;
	
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doctorId")
	private Doctor doctor;

	public int getDiagnosticId() {
		return DiagnosticId;
	}

	public void setDiagnosticId(int diagnosticId) {
		DiagnosticId = diagnosticId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public Date getDiagnosticDate() {
		return DiagnosticDate;
	}

	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDiagnosticDate(Date diagnosticDate) {
		DiagnosticDate = diagnosticDate;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Diagnostic() {
	}

	public Diagnostic(int diagnosticId, String note, Employee employee, Doctor doctor) {
		super();
		DiagnosticId = diagnosticId;
		this.note = note;
		LocalDate d=LocalDate.now();
		this.DiagnosticDate=(Date) java.util.Calendar.getInstance().getTime();
		this.employee = employee;
		this.doctor = doctor;
	}
}
