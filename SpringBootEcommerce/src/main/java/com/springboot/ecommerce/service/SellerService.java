package com.springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.repository.SellerRepository;




@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepository;
	
	public Seller postSeller(Seller seller) {
		return sellerRepository.save(seller);
	}

	public List<Seller> getAllSeller() {
		List<Seller> list = sellerRepository.findAll();
		return list;
		 
	}
	public Seller getSellerById(int id) throws InvalidIdException{
		java.util.Optional<Seller> optional = sellerRepository.findById(id);
		if(!optional.isPresent())
			throw new InvalidIdException(" Id Invalid");
	    Seller seller = optional.get();
		return seller;
	}

	public void deleteSeller(Seller seller) {
	    sellerRepository.delete(seller);
		
	}

	public Seller insert(Seller seller) {

		return sellerRepository.save(seller);
	}

	

  
}
