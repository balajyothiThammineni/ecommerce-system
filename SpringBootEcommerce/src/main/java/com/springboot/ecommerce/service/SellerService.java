package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.repository.SellerRepository;




@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepository;
	
	
	public Seller insert(Seller seller) {
	      return sellerRepository.save(seller);
		}
	
	public List<Seller> getAllSeller() {
		List<Seller> list = sellerRepository.findAll();
		return list;
		 
	}

	
	public Seller postSeller(Seller seller) {
		return sellerRepository.save(seller);
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

	

	public Seller getOne(int id) throws InvalidIdException {
		Optional<Seller> optional =  sellerRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Seller ID Invalid");
		}
		return optional.get();
	}

	public Seller getById(int sid) throws InvalidIdException{
		Optional<Seller>optional = sellerRepository.findById(sid);
		if(!optional.isPresent())
			throw new InvalidIdException("sid Invalid");
		return optional.get();
	}



}
