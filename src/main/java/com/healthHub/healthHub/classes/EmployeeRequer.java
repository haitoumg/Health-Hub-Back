package com.healthHub.healthHub.classes;

public class EmployeeRequer extends PersonneRequer{
	private int numEmployee;

	public int getnumEmployee() {
		return numEmployee;
	}

	public void setnumEmployee(int numEmployee) {
		this.numEmployee = numEmployee;
	}

	public EmployeeRequer() {
		super();
	}

	public EmployeeRequer(int numEmployee) {
		super();
		this.numEmployee = numEmployee;
	}
	
	
}
