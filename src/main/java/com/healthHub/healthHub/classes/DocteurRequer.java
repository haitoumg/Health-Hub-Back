package com.healthHub.healthHub.classes;


public class DocteurRequer extends PersonneRequer{
	private int numDocteur;
	private String specialité;

	public int getNumDocteur() {
		return numDocteur;
	}

	public void setNumDocteur(int numDocteur) {
		this.numDocteur = numDocteur;
	}

	public String getSpecialité() {
		return specialité;
	}

	public void setSpecialité(String specialité) {
		this.specialité = specialité;
	}

	public DocteurRequer() {
		super();
	}

	public DocteurRequer(int numDocteur, String specialité) {
		super();
		this.numDocteur = numDocteur;
		this.specialité = specialité;
	}

}
