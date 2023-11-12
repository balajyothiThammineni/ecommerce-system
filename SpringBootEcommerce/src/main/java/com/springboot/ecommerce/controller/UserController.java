package com.springboot.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	private UserService userservice;  // Data Integration
	
	@PostMapping("/add")   
	public User postUser(@RequestBody User user) {
		user =userservice.postUser(user);
		return user;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id")int id){
		try {
			User user=userservice.getUserById(id);
		    return ResponseEntity.ok().body(user);
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/getall")
    public List<User> getAllUsers(){
		return userservice.getAll();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		
		try {
			//validate id
			User user = userservice.getUserById(id);
			//delete
			userservice.deleteUser(user);
			return ResponseEntity.ok().body(" deleted successfully");
         } catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}


	
	}	
}

