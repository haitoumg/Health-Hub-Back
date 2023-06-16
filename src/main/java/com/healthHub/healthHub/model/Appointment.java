package com.healthHub.healthHub.model;

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
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;

	@Column(nullable = false)
	private Date dateAppointment;


	@Column(nullable = false, columnDefinition = "BOOLEAN")
	private boolean cancelled;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "personneId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "calendarId")
	private Calendar calendar;

	public Appointment() {

	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public Appointment(int appointmentId, boolean accept, Date dateAppointment, boolean cancelled, Employee employee,
			Calendar calendar) {
		super();
		this.appointmentId = appointmentId;
		this.dateAppointment = dateAppointment;
		this.cancelled = cancelled;
		this.employee = employee;
		this.calendar = calendar;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(Date dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
