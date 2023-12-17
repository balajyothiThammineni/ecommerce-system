package com.springboot.ecommerce.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Executive;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.ExecutiveService;
import com.springboot.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Logger logger;
	
	
	@PostMapping("/executive/signup")
	public ResponseEntity<?> addExecutive(@RequestBody Executive executive) {
	    // Check if the email already exists in the Executive table
	    String email = executive.getExecutiveEmail();
	    if (executiveService.existsByexecutiveEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }

	    User user = executive.getUser();
	    String password = user.getPassword();
	    String encodedPassword = passwordEncoder.encode(password);
	    user.setPassword(encodedPassword);
	    user.setRole(Role.EXECUTIVE);
	    user = userService.insert(user);
	    executive.setUser(user);

	    

	    // Insert the executive into the database
	    Executive insertedExecutive = executiveService.insert(executive);

	    logger.info("Executive signed up: {}", insertedExecutive.getExecutiveName());

	    // Return a success message along with the created executive
	    return ResponseEntity.status(HttpStatus.OK).body(insertedExecutive);
	}

	
	
	

    
	
	
}
