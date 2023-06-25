package com.healthHub.healthHub.classes;

import java.sql.Time;
import java.util.Date;

public class AppointmentInfos {
    private String startDate;
    private String endDate;
    private String description;
    private String hub;
    private int appointmentId;
    private boolean cancelled;
    private String cancelledBy;
    private String fullName;
    public AppointmentInfos(String startDate, String endDate,int appointmentId, String fullName, boolean cancelled, String cancelledBy){
        this.startDate = startDate;
        this.endDate = endDate;
        this.appointmentId = appointmentId;
        this.fullName=fullName;
        this.cancelled=cancelled;
        this.cancelledBy=cancelledBy;
    }

    public AppointmentInfos(String startDate, String endDate, String description, String hub, int appointmentId, boolean cancelled, String cancelledBy) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.hub = hub;
        this.appointmentId = appointmentId;
        this.cancelled=cancelled;
        this.cancelledBy=cancelledBy;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getHub() {
        return hub;
    }

    public int getAppointmentId() {
        return appointmentId;
    }
}
