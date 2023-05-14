package com.healthHub.healthHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.healthHub.healthHub.classes.EmailSenderService;
import com.healthHub.healthHub.classes.Mail;


/* Created by Arjun Gautam */
@RestController
@CrossOrigin("http://localhost:3000")
public class MainController {
    @Autowired
	private EmailSenderService senderService;
    
    @GetMapping("/sendMail")
    public String triggerMail(@RequestBody Mail m) {
    	senderService.sendSimpleEmail(m.getFrom(),
				m.getSubject(),
				m.getBody());
		return "Sending";

	}




}
