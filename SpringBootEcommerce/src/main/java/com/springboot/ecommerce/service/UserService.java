package com.springboot.ecommerce.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	private UserRepository userRepository;
	
    public User insert(User user) {
		return userRepository.save(user);
	}
	
	

}
