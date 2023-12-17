package com.springboot.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.AddressService;
import com.springboot.ecommerce.service.CustomerService;
import com.springboot.ecommerce.service.UserService;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	
    @Autowired 
    private AddressService addressService;
    
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Logger logger;
	
	@PostMapping("/customer/signup")
	public ResponseEntity<?> postCustomer(@RequestBody Customer customer) {
	    // Check if the email already exists in the Customer table
	    String email = customer.getCustomerEmail();
	    if (customerService.existsByCustomerEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }

	    // If the email doesn't exist, proceed with the signup process
	    User user = customer.getUser();
	    String password = user.getPassword();
	    String encodedpassword = passwordEncoder.encode(password);
	    user.setPassword(encodedpassword);
	    user.setRole(Role.CUSTOMER);
	    user = userService.insert(user);
	    customer.setUser(user);
	    logger.info("Customer signed up: {}",customer.getCustomerName());
	  

	    // Return a success message along with the created customer
	    return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	

	
    @GetMapping("/customer/all")
	public List<Customer> getAllCustomers() { 
		List<Customer> list = customerService.getAllCustomers();
		return list;
	}

    @GetMapping("/customer/getByUserId/{userId}")
	public ResponseEntity<?> getCustomerByUserId(@PathVariable("userId") int userId) {
		try {
			Optional<Customer> customer = customerService.getCustomerByUserId(userId);
			if(customer.isPresent()){
				return ResponseEntity.ok().body(customer);
			}else{
				return null;
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	@DeleteMapping("/customer/delete/{id}") /* 8080/customer/delete/{id} */
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {

		try {
			// validate id
			Customer customer = customerService.getCustomerById(id);
			// delete
			customerService.deleteCustomer(customer);
			return ResponseEntity.ok().body(" Account deleted successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/customer/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody Customer newCustomer) 
			throws InvalidIdException {
		Customer oldCustomer = customerService.getCustomer(id);
		if (newCustomer.getCustomerName() != null)
			oldCustomer.setCustomerName(newCustomer.getCustomerName());
		if (newCustomer.getCustomerEmail() != null)
			oldCustomer.setCustomerEmail(newCustomer.getCustomerEmail());
		if (newCustomer.getCustomerNumber() != null)
			oldCustomer.setCustomerNumber(newCustomer.getCustomerNumber());
		oldCustomer = customerService.insert(oldCustomer);
		return ResponseEntity.ok().body(oldCustomer);
	}

	@GetMapping("/customer/products/{sid}")
	public ResponseEntity<?> getCustomerBySeller(@PathVariable ("sid") int sid){
		List<Customer> customers = customerService.getCustomerBySeller(sid);
		return ResponseEntity.ok().body(customers);
	}



 

}

