package com.healthHub.healthHub.classes;

public class EmployeRequer extends PersonneRequer{
	private int numEmploye;

	public int getNumEmploye() {
		return numEmploye;
	}

	public void setNumEmploye(int numEmploye) {
		this.numEmploye = numEmploye;
	}

	public EmployeRequer() {
		super();
	}

	public EmployeRequer(int numEmploye) {
		super();
		this.numEmploye = numEmploye;
	}
	
	
}
