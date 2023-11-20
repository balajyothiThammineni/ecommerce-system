package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.repository.CustomerRepository;


@SuppressWarnings("unused")
@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer postCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	public List<Customer> getAllCustomers() {
		List<Customer> list = customerRepository.findAll();
		return list;
		 
	}
	public Customer getCustomerById(int id) throws InvalidIdException{
		java.util.Optional<Customer> optional = customerRepository.findById(id);
		if(!optional.isPresent())
			throw new InvalidIdException("Customer Id Invalid");
		Customer customer = optional.get();
		return customer;
	}
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
		
	}
	public Customer insert(Customer customer) {

		return customerRepository.save(customer);

	}
	
	public Customer getCustomer(int id){  //review by cid and pid
		Customer optional = customerRepository.findById(id).get();
		return optional;
	}
	
		
	}
	