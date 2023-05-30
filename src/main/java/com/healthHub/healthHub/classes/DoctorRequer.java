package com.healthHub.healthHub.classes;


public class DoctorRequer extends PersonneRequer{
	private int numDoctor;
	private String specialty;

	public int getNumDoctor() {
		return numDoctor;
	}

	public void setNumDoctor(int numDoctor) {
		this.numDoctor = numDoctor;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public DoctorRequer() {
		super();
	}

	public DoctorRequer(int numDoctor, String specialty) {
		super();
		this.numDoctor = numDoctor;
		this.specialty = specialty;
	}

}
