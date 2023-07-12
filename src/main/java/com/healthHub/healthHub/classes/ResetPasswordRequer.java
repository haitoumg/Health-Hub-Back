package com.healthHub.healthHub.classes;

public class ResetPasswordRequer {
	private String email;
	private String newPassword;
	private String confirmedPassword;
	private String confirmationCode;


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public ResetPasswordRequer() {
		super();
	}
	public ResetPasswordRequer(String email, String oldPassword, String newPassword, String confirmedPassword) {
		super();
		this.email = email;
		this.newPassword = newPassword;
		this.confirmedPassword = confirmedPassword;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	
	public String getConfirmationCode() {
	    return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
	    this.confirmationCode = confirmationCode;
	}

}
