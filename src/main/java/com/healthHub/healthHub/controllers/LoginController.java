package com.healthHub.healthHub.controllers;

import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import com.healthHub.healthHub.classes.ResetPasswordRequer;
import com.healthHub.healthHub.classes.ErrorResponse;
import com.healthHub.healthHub.classes.loginRequer;
import com.healthHub.healthHub.classes.PersonneLogin;
import com.healthHub.healthHub.model.Personne;
import com.healthHub.healthHub.repository.PersonneRepository;
import com.healthHub.healthHub.classes.Mail;
import com.healthHub.healthHub.classes.EncryptionUtils;

@RestController
@CrossOrigin("http://localhost:3000")
public class LoginController {

	private final PersonneRepository personneRepository;

	@Autowired
	public LoginController(PersonneRepository personneRepository) {
		this.personneRepository = personneRepository;
	}

	public static String generateRandomString() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

	@GetMapping("/personne")
	public ResponseEntity<Personne> getUserByLogin(@RequestParam String login) {
		Optional<Personne> personne = personneRepository.findByemail(login);
		if (personne.isPresent()) {
			return ResponseEntity.ok(personne.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> getPersonneBylogin(@RequestBody loginRequer login) {
		Optional<Personne> personne = personneRepository.findByemail(login.getEmail());
		if (personne.isPresent() && bcrypt.matches(login.getPassword(), personne.get().getPassword())) {
			PersonneLogin p = new PersonneLogin(personne.get().getPersonneId(), personne.get().getlastName(),
					personne.get().getfirstName(), personne.get().getBirthDate(), personne.get().getTelephone(),
					personne.get().getEmail(), personne.get().getHub().getHubId(), personne.get().getRole());
			p.setHubName(personne.get().getHub().getHubName());
			p.setHubCity(personne.get().getHub().getCity());
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			String errorMessage = "Incorrect Email or Password";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/resetpasswordrequest")
	public ResponseEntity<?> resetPasswordRequest(@RequestBody loginRequer login) {

		Optional<Personne> personne = personneRepository.findByemail(login.getEmail());
		if (personne.isPresent()) {
			Mail mail = new Mail();
			mail.setTo(login.getEmail());
			mail.setSubject("Password Reset");
			String encryptedEmail = EncryptionUtils.encrypt(login.getEmail());
			String link = "http://localhost:3000/ResetPasswordForm/" + encryptedEmail;
			mail.setBody("To reset your password click on the link : " + link);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Mail> requestEntity = new HttpEntity<>(mail, headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:9090/sendMail",
					HttpMethod.POST, requestEntity, String.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				return new ResponseEntity<>(personne, HttpStatus.OK);
			} else {
				String errorMessage = "Failed to send email";
				ErrorResponse errorResponse = new ErrorResponse(errorMessage);
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			String errorMessage = "Email not found";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}

	}
	@PutMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequer changePwdRequest) {
	    try {
	    	String decryptedEmail = EncryptionUtils.decrypt(changePwdRequest.getEmail());
			Optional<Personne> personne = personneRepository.findByemail(decryptedEmail);
	            if (personne.isPresent()) {
	                Personne existingPersonne = personne.get();
	                existingPersonne.setPassword(bcrypt.encode(changePwdRequest.getNewPassword()));
	                Personne personneUpdated = personneRepository.save(existingPersonne);
	                return new ResponseEntity<>(personneUpdated, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/validateemail/{encryptedEmail}")
	public ResponseEntity<String> validateEmail(@PathVariable("encryptedEmail") String encryptedEmail) {
	    try {
	        String decryptedEmail = EncryptionUtils.decrypt(encryptedEmail);
	        Optional<Personne> personne = personneRepository.findByemail(decryptedEmail);
	        if (personne.isPresent()) {
	            return ResponseEntity.ok("Email is valid");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email");
	}


}