package com.healthHub.healthHub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.classes.EmployeeRequer;
import com.healthHub.healthHub.model.Hub;
import com.healthHub.healthHub.model.Employee;
import com.healthHub.healthHub.repository.HubRepository;
import com.healthHub.healthHub.repository.EmployeeRepository;


@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
	private final EmployeeRepository employeeRepository;
	private final HubRepository hubRepository;

	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository,HubRepository hubRepository) {
		this.hubRepository = hubRepository;
		this.employeeRepository = employeeRepository;
	}
	

	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmploye(@RequestBody EmployeeRequer employeRequ) {
		//Creation of object from the hashing class
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryPwd= bcrypt.encode(employeRequ.getPassword());
		Employee employee=new Employee(employeRequ.getnumEmployee());
		//saving employee with hashed password
		employee.setPassword(encryPwd);
		employee.setEmail(employeRequ.getEmail());
		employee.setBirthDate(employeRequ.getBirthDate());
		employee.setlastName(employeRequ.getlastName());
		employee.setfirstName(employeRequ.getfirstName());
		employee.setRole("Employee");
		employee.setTelephone(employeRequ.getTelephone());
		long id=employeRequ.getIdHub();
		Optional<Hub> optionalHub = hubRepository.findById(id);
	    if (optionalHub.isPresent()) {
	    	employee.setHub(optionalHub.get());
	    }
		Employee createdEmploye = employeeRepository.save(employee);
		return new ResponseEntity<>(createdEmploye, HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployes() {
	    List<Employee> Employes = employeeRepository.findAll();
	    return new ResponseEntity<>(Employes, HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeById(@PathVariable("id") Long id) {
		Optional<Employee> Employe = employeeRepository.findById(id);
		if (Employe.isPresent()) {
			return new ResponseEntity<>(Employe.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmploye(@PathVariable("id") Long id, @RequestBody EmployeeRequer updatedEmploye) {
	    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	    if (optionalEmployee.isPresent()) {
	    	Employee existingEmploye = optionalEmployee.get();
	    	existingEmploye.setnumEmployee(updatedEmploye.getnumEmployee());
	        existingEmploye.setlastName(updatedEmploye.getlastName());
	        existingEmploye.setfirstName(updatedEmploye.getfirstName());
	        existingEmploye.setBirthDate(updatedEmploye.getBirthDate());
	        existingEmploye.setTelephone(updatedEmploye.getTelephone());
	        existingEmploye.setEmail(updatedEmploye.getEmail());
	        existingEmploye.setPassword(updatedEmploye.getPassword());
	        long idC=updatedEmploye.getIdHub();
			Optional<Hub> optionalHub = hubRepository.findById(idC);
		    if (optionalHub.isPresent()) {
		    	existingEmploye.setHub(optionalHub.get());
		    }
	        Employee savedEmploye = employeeRepository.save(existingEmploye);
	        return new ResponseEntity<>(savedEmploye, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Void> deleteEmploye(@PathVariable("id") Long id) {
	    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	    if (optionalEmployee.isPresent()) {
	    	employeeRepository.delete(optionalEmployee.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
