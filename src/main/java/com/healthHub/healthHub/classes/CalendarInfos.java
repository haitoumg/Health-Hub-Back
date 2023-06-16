package com.healthHub.healthHub.classes;

import java.sql.Time;
import java.util.Date;

public class CalendarInfos {
    private Date workingDay;
    private Time startTime;
    private Time endTime;
    private boolean isBooked;



    public CalendarInfos(Date workingDay, Time startTime, Time endTime, String employeeLastName, String employeeFirstName,Boolean isBooked) {
        this.workingDay = workingDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employeeLastName = employeeLastName;
        this.employeeFirstName = employeeFirstName;
        this.isBooked=isBooked;
    }
    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
    public void setWorkingDay(Date workingDay) {
        this.workingDay = workingDay;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public Date getWorkingDay() {
        return workingDay;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    private String employeeLastName;
    private String employeeFirstName;
}
