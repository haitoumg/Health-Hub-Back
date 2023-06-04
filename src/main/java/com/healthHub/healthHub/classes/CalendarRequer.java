package com.healthHub.healthHub.classes;

import java.sql.Time;
import java.util.Date;

public class CalendarRequer {
	private Date workingDay;
	private Time startTime;
	private Time endTime;
	private long doctorId;

	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
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
	public CalendarRequer() {

	}
	public CalendarRequer(Date workingDay, Time startTime, Time endTime, long doctorId) {
		super();
		this.workingDay = workingDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.doctorId = doctorId;
	}
}
