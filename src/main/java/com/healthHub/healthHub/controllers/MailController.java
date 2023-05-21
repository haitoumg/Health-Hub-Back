package com.healthHub.healthHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.healthHub.healthHub.classes.Mail;
import com.healthHub.healthHub.service.MailSenderService;


/* Created by Arjun Gautam */
@RestController
@CrossOrigin("http://localhost:3000")
public class MailController {
	private final MailSenderService emailSenderService;
    @Autowired
    public MailController(MailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
    
    @GetMapping("/sendMail")
    public String triggerMail(@RequestBody Mail m) {
    	 String to = m.getTo();
         String subject = m.getSubject();
         String text = m.getBody();

         // Send the email
        emailSenderService.sendSimpleEmail(to, subject, text);
		return "Sending";

	}




}