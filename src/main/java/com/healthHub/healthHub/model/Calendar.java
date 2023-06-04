package com.healthHub.healthHub.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CalendarId;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date workingDay;

	@Column(nullable = false)
	private Time startTime;

	@Column(nullable = false)
	private Time endTime;

	public Calendar() {

	}

	public int getCalendarId() {
		return CalendarId;
	}

	public void setCalendarId(int CalendarId) {
		this.CalendarId = CalendarId;
	}

	public Date getworkingDay() {
		return workingDay;
	}

	public void setworkingDay(Date workingDay) {
		this.workingDay = workingDay;
	}

	public Time getstartTime() {
		return startTime;
	}

	public void setstartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getendTime() {
		return endTime;
	}

	public void setendTime(Time endTime) {
		this.endTime = endTime;
	}

	public Calendar(int CalendarId, Date workingDay, Time startTime, Time endTime, Doctor doctor,
			List<Appointment> appointement) {
		super();
		this.CalendarId = CalendarId;
		this.workingDay = workingDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.doctor = doctor;
		this.appointement = appointement;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Appointment> getappointement() {
		return appointement;
	}

	public void setappointement(List<Appointment> appointement) {
		this.appointement = appointement;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "personneId")
	private Doctor doctor;

	@OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Appointment> appointement;
}
