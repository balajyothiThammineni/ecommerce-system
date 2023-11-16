package com.springboot.ecommerce.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.UserService;


public class AuthController {
	@Autowired
	private UserService userService;

	@GetMapping("/user/getall")
	public User login(Principal  principal) {  
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		return user; 

}

}



