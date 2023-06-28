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

	/*
	 * String TimeporaryPwd = generateRandomString(); String encryTimporaryPwd
	 * =bcrypt.encode(TimeporaryPwd);
	 * 
	 * Personne existingPersonne = personne.get();
	 * existingPersonne.setPassword(encryTimporaryPwd); Personne savedPersonne
	 * =personneRepository.save(existingPersonne);
	 */

	@PostMapping("/resetpasswordrequest")
	public ResponseEntity<?> resetPasswordRequest(@RequestBody loginRequer login) {

		Optional<Personne> personne = personneRepository.findByemail(login.getEmail());
		if (personne.isPresent()) {
			System.out.println("Enter to reset password controller");

			Mail mail = new Mail();
			mail.setTo(login.getEmail());
			mail.setSubject("Password Reset");
			String link = "http://localhost:3000/ResetPasswordForm/" + login.getEmail();
			//Mail hashed like password in the link
			// mail.setBody("Your temporary password is: " + TimeporaryPwd+"\n We highly
			// encourage you to change your password in the application!");
			mail.setBody("To reset your password click on the link : " + link);
			System.out.println(link + "is the link sent");

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
	//change password inside app
	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody ResetPasswordRequer changePwdRequest) {
		System.out.println("Enter to change password controller");
		Optional<Personne> personne = personneRepository.findByemail(changePwdRequest.getEmail());

		if (changePwdRequest.getNewPassword().equals(changePwdRequest.getConfirmedPassword())) {
			if (personne.isPresent()
					&& bcrypt.matches(changePwdRequest.getOldPassword(), personne.get().getPassword())) {
				System.out.println("Old Password and password in DB are matched");
				Personne existingPersonne = personne.get();
				existingPersonne.setPassword(bcrypt.encode(changePwdRequest.getNewPassword()));
				Personne personneUpdated = personneRepository.save(existingPersonne);
				System.out.println("Update password of person" + changePwdRequest.getNewPassword());
				System.out.println("DB updated");
				return new ResponseEntity<>(personneUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@PutMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequer changePwdRequest) {
		Optional<Personne> personne = personneRepository.findByemail(changePwdRequest.getEmail());

		if (changePwdRequest.getNewPassword().equals(changePwdRequest.getConfirmedPassword())) {
			if (personne.isPresent()) {
				Personne existingPersonne = personne.get();
				existingPersonne.setPassword(bcrypt.encode(changePwdRequest.getNewPassword()));
				Personne personneUpdated = personneRepository.save(existingPersonne);
				return new ResponseEntity<>(personneUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/validateemail/{email}")
	public ResponseEntity<String> validateToken(@PathVariable("email") String email) {
		
		Optional<Personne> personne = personneRepository.findByemail(email);
		if (personne.isPresent()) {
			return ResponseEntity.ok("email is valid");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email");
	}
}











/*
 * @GetMapping("/validateemail/{email}") public ResponseEntity<String>
 * validateToken(@PathVariable("email") String email) { Optional<Personne>
 * personne = personneRepository.findByemail(email); if (personne.isPresent()) {
 * return ResponseEntity.ok("Email is valid"); }
 * 
 * // Email is valid, you can perform additional actions if needed
 * 
 * return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Email"); }
 */

/*

*/

// changepassword
// verify old password
// Double validation password

/*
 * @PutMapping("/changepassword") public ResponseEntity<?>
 * changePassword(@RequestBody loginRequer login) {
 * 
 * Optional<Personne> personne =
 * personneRepository.findByemail(login.getEmail()); if (personne.isPresent()) {
 * 
 * 
 * 
 * Mail email = new Mail(); email.setTo(login.getEmail());
 * email.setSubject("Password Change Notification");
 * email.setBody("Your temporary password is"
 * +TimeporaryPwd+"\n Please, reset your password in the application!" );
 * 
 * 
 * BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(); String encryPwd =
 * bcrypt.encode(TimeporaryPwd); Personne existingPersonne = personne.get();
 * existingPersonne.setPassword(encryPwd);
 * existingPersonne.setPassword_temporary(true); Personne savedPersonne =
 * personneRepository.save(existingPersonne);
 * 
 * 
 * return new ResponseEntity<>(savedPersonne, HttpStatus.OK); } else { String
 * errorMessage = "Email not found"; ErrorResponse errorResponse = new
 * ErrorResponse(errorMessage); return new ResponseEntity<>(errorResponse,
 * HttpStatus.NOT_FOUND); } }
 */

/*
 * @PostMapping("/login") public ResponseEntity<?>
 * getPersonneBylogin(@RequestBody loginRequer login) {
 * 
 * // Get the password of the person (crypted and saved in DB)
 * 
 * // Call the same class for encryption BCryptPasswordEncoder bcrypt = new
 * BCryptPasswordEncoder(); Optional<Personne> personne =
 * personneRepository.findByemail(login.getEmail()); // There's no decrypting
 * method in this class => use matching passwords (raw, // crypted)
 * 
 * if (personne.isPresent() && bcrypt.matches(login.getPassword(),
 * personne.get().getPassword()) && !login.isPassword_temporary() ) { //redirect
 * to login form System.out.println("Redirect to login form form");
 * 
 * PersonneLogin p = new PersonneLogin(personne.get().getPersonneId(),
 * personne.get().getlastName(), personne.get().getfirstName(),
 * personne.get().getBirthDate(), personne.get().getTelephone(),
 * personne.get().getEmail(), personne.get().getHub().getHubId(),
 * personne.get().getRole());
 * p.setHubName(personne.get().getHub().getHubName());
 * p.setHubCity(personne.get().getHub().getCity()); return new
 * ResponseEntity<>(p, HttpStatus.OK); } else if(personne.isPresent() &&
 * bcrypt.matches(login.getPassword(), personne.get().getPassword()) &&
 * login.isPassword_temporary()) {
 * System.out.println("Redirect to reset password form"); //rediret to reset
 * password form String password = login.getPassword(); String confirmPwd =
 * login.getConfirmPwd(); // Perform server-side validation if
 * (!password.equals(confirmPwd)) { return ResponseEntity.badRequest().
 * body("Password and confirmed password must match!"); } else {
 * BCryptPasswordEncoder bcryptPwd = new BCryptPasswordEncoder(); String
 * encryPwd = bcryptPwd.encode(password); Personne existingPersonne =
 * personne.get(); existingPersonne.setPassword(encryPwd);
 * existingPersonne.setPassword_temporary(false); Personne savedPersonne =
 * personneRepository.save(existingPersonne); return new
 * ResponseEntity<>(savedPersonne, HttpStatus.OK);
 * 
 * } //return to login again
 * 
 * } else { String errorMessage = "Incorrect Email or Password"; ErrorResponse
 * errorResponse = new ErrorResponse(errorMessage); return new
 * ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); } }
 * 
 * 
 */