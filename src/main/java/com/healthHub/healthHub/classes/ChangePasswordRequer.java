package com.healthHub.healthHub.classes;

public class ChangePasswordRequer {
	private String email;
	private String currentPassword;
	private String newPassword;
	private String confirmedPassword;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public ChangePasswordRequer(String email, String currentPassword, String newPassword, String confirmedPassword) {
		super();
		this.email = email;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.confirmedPassword = confirmedPassword;
	}
	public ChangePasswordRequer() {
		super();
	}
}
