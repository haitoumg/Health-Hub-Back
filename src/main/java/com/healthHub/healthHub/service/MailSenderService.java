package com.healthHub.healthHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	private final JavaMailSender mailSender;

	@Autowired
	public MailSenderService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendSimpleEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ayoubbensbih399@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
		System.out.println("Mail Sent...");

	}

}