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
	
	public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

	
	
	public Customer postCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	public List<Customer> getAllCustomers() {
		List<Customer> list = customerRepository.findAll();
		return list;	 
	}
	
	
	public Customer getCustomerById(int id) throws InvalidIdException {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isEmpty()) {
            throw new InvalidIdException("Customer Id Invalid");
        }
        return optional.get();
    }
	
	
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
		
	}
	
	
	public Customer insert(Customer customer) {

		return customerRepository.save(customer);

	}
	
	
	
	public Customer getCustomer(int id){  
		Customer optional = customerRepository.findById(id).get();
		return optional;
	}


	public List<Customer> getCustomerBySeller(int sid) {
		
		return customerRepository.getCustomerBySeller(sid) ;
	}


	


	public boolean existsByCustomerEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.existsByCustomerEmail(email);
	}


	public Customer getOne(int customerId) {
		// TODO Auto-generated method stub
		return customerRepository.getOne(customerId);
	}


	public Optional<Customer> getCustomerByUserId(int customerId){
		return customerRepository.getCustomerByUserId(customerId);
	}


	



		
	}
	