package com.springboot.ecommerce.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User postUser(User user) {
		// I am giving this user object to repository
		return userRepository.save(user);
	}


	public User getUserById(int id) throws InvalidIdException{
	Optional<User>optional=userRepository.findById(id);
	if(!optional.isPresent()) {
	throw new InvalidIdException("Invalid Credentials");
	}
	return optional.get();
}


	public List<User> getAll() {
	return userRepository.findAll();
	}
}
