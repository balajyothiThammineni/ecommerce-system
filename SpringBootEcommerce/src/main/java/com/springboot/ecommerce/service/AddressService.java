
package com.springboot.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Address;
import com.springboot.ecommerce.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address insert(Address address) {
		return addressRepository.save(address);
		
	}

	public Address getAddress(int id) throws InvalidIdException{
		Optional<Address>optional =addressRepository.findById(id);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Address Id Inavalid");		
		}
		return optional.get();
	}

	public Address postAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
