package com.healthHub.healthHub.classes;

public class loginRequer {
	private String email;
	private String motdepass;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotdepass() {
		return motdepass;
	}
	public void setMotdepass(String motdepass) {
		this.motdepass = motdepass;
	}
	public loginRequer() {
		super();
	}
	public loginRequer(String email, String motdepass) {
		super();
		this.email = email;
		this.motdepass = motdepass;
	}
}
