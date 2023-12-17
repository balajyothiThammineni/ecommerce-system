package com.springboot.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.ecommerce.controller.CustomerController;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.repository.CustomerRepository;
import com.springboot.ecommerce.service.CustomerService;



@SpringBootTest
class SpringBootEcommerceApplicationTests {
	
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private CustomerService customerService;
	@Autowired
	private CustomerController customerController;
	
	@Test
	public void testPostCustomer() {
		Customer customer = new Customer();
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.postCustomer(customer);
		assertNotNull(result);
		assertEquals(customer, result);
	}
	
	@Test
	public void testDeleteCustomer() {
		Customer customer = new Customer();
		customerService.deleteCustomer(customer);
		verify(customerRepository, times(1)).delete(customer);
	}


	}



