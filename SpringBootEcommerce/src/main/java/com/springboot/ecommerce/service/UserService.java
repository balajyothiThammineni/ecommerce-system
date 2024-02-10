package com.springboot.ecommerce.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.repository.CustomerRepository;
import com.springboot.ecommerce.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);

	}

	public User insert(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public void sendEmailOnRegistration(int userId) throws InvalidIdException {
	    Optional<Customer> optional = customerRepository.findById(userId);
	    
	    if (!optional.isPresent()) {
	        throw new InvalidIdException("id not found");
	    }
	    
	    Customer customer = optional.get();
	    // Assuming userRepository.findById method requires a parameter, replace it accordingly
	    // User user = userRepository.findById(customer.getId()).orElse(new User());
	    
	    String subject = "Registration confirmation";
	    String text = "Dear " + customer.getCustomerEmail() + ",\n\n" +
	            "Welcome to EPIC PICS -Have a great experience with our platform .\n\n" +
	            "Explore the best collections and great deels .\n\n" +
	            "Thank you for choosing us. Daily deals with exciting offers!\n\n" +
	            "Warm regards";
	           
 
	    // Assuming mailSender is an instance of JavaMailSender
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(customer.getCustomerEmail());
	    message.setSubject(subject);
	    message.setText(text);
	    mailSender.send(message);
	}
	
	public void sendEmailOnOrderPlaced(int userId) throws InvalidIdException {
	    Optional<Customer> optional = customerRepository.findById(userId);
	    
	    if (!optional.isPresent()) {
	        throw new InvalidIdException("id not found");
	    }
	    
	    Customer customer = optional.get();
	    // Assuming userRepository.findById method requires a parameter, replace it accordingly
	    // User user = userRepository.findById(customer.getId()).orElse(new User());
	    
	    String subject = "Registration confirmation";
	    String text = "Dear " + customer.getCustomerEmail() + ",\n\n" +
	            "Your order has been placed successfully\n\n" +
	            
	            "Thank you for choosing us. Daily deals with exciting offers!\n\n" +
	            "Continue shopping with us";
	           
 
	    // Assuming mailSender is an instance of JavaMailSender
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(customer.getCustomerEmail());
	    message.setSubject(subject);
	    message.setText(text);
	    mailSender.send(message);
	}

	
	
}

