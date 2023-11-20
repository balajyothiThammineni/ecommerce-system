package com.springboot.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Address;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.AddressService;
import com.springboot.ecommerce.service.CustomerService;
import com.springboot.ecommerce.service.UserService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	
    @Autowired 
    private AddressService addressService;
    
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/customer/signup")
	public Customer signUp(@RequestBody Customer customer) {

		User user = customer.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.CUSTOMER);
		user = userService.insert(user);
		customer.setUser(user);
        Address address = addressService.postAddress(customer.getAddress());
		customer.setAddress(address);
		return customerService.insert(customer);
	}
	
	

	
    @GetMapping("/customer/all")
	public List<Customer> getAllCustomers() { 
		List<Customer> list = customerService.getAllCustomers();
		return list;
	}

	@GetMapping("/customer/login/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
		try {
			Customer customer = customerService.getCustomerById(id);
			return ResponseEntity.ok().body(customer);
		} catch (InvalidIdException e) {
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
	
	
}
