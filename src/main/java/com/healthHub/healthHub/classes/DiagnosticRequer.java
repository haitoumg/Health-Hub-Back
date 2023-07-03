package com.healthHub.healthHub.classes;
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
