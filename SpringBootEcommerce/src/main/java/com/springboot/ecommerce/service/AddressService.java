package com.springboot.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.model.Address;
import com.springboot.ecommerce.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	public Address postAddress(Address address) {
		return addressRepository.save(address);
	}
		
}

