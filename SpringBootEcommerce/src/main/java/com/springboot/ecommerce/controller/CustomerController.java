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

	@PostMapping("/customer/add")
	public Customer insertCustomer(@RequestBody Customer customer ) { // method is mapped to the url
		User user = customer.getUser();
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		userService.postUser(user);
		/* saving address with id */
		Address address = customer.getAddress();
		addressService.insert(address); // customer info as an object and give it to the repository via service
		return customerService.insert(customer);

	}
    @GetMapping("/customer/all")
	public List<Customer> getAllCustomers() { 
		List<Customer> list = customerService.getAllCustomers();
		return list;
	}

	@GetMapping("/customer/one/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
		try {
			Customer customer = customerService.getCustomerById(id);
			return ResponseEntity.ok().body(customer);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/customer/delete/{id}") /* 8080/customer/delete/13 */
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {

		try {
			// validate id
			Customer customer = customerService.getCustomerById(id);
			// delete
			customerService.deleteCustomer(customer);
			return ResponseEntity.ok().body(" deleted successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/customer/update/{id}") /* value for update 8080/customer/update/19 */
	public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody Customer newCustomer) {
		try {
			// validate id
			Customer customer = customerService.getCustomerById(id);
			if (newCustomer.getCustomerPassword() != null)
				customer.setCustomerName(newCustomer.getCustomerPassword());
			if (newCustomer.getCustomerEmail() != null)
				customer.setCustomerEmail(newCustomer.getCustomerEmail());
			if (newCustomer.getCustomerPassword() != null)
				customer.setCustomerPassword(newCustomer.getCustomerPassword());

			customer = customerService.insert(customer);
			return ResponseEntity.ok(customer);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
