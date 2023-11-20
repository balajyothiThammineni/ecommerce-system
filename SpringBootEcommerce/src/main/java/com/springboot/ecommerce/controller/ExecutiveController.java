package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/executive/signup")
	public Executive addExecucutive(@RequestBody Executive executive) {
		 
		User user = executive.getUser();
		String passwordPlain =user.getPassword();
		String encodedPassword =passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.EXECUTIVE);
		user=userService.insert(user);
		executive.setUser(user);
		return executiveService.insert(executive);
		}	
	
	
	
    @GetMapping("/executive/login/{id}")
	public ResponseEntity<?> getExecutive(@PathVariable("id") int id) {

		try {
			Executive executive = executiveService.getExecutive(id);
			return ResponseEntity.ok().body(executive);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
    
	
	
}
