package com.healthHub.healthHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.healthHub.healthHub.classes.EmailSenderService;
import com.healthHub.healthHub.classes.Mail;


/* Created by Arjun Gautam */
@RestController
@CrossOrigin("http://localhost:3000")
public class MainController {
	private final EmailSenderService emailSenderService;
    @Autowired
    public MainController(EmailSenderService emailSenderService) {
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
