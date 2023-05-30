package com.healthHub.healthHub.classes;

import java.sql.Date;

public class AppointmentRequer {

	private Date dateAppointment;

	private long employeeId;

	private long calendarId;

	public AppointmentRequer() {
		super();
	}

	public AppointmentRequer(Date dateAppointment, long employeeId, long calendarId) {
		super();

		this.dateAppointment = dateAppointment;
		this.employeeId = employeeId;
		this.calendarId = calendarId;
	}

	public Date getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(Date dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(long calendarId) {
		this.calendarId = calendarId;
	}

}
