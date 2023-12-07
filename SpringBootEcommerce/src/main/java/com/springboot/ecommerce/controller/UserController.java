package com.springboot.ecommerce.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("/auth/login")
//	public User login(Principal principal) {
//		String username = principal.getName();
//		User user  = userService.getUserByUserName(username);
//		return user; 
//	}
}


