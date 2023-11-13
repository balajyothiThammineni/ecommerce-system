package com.springboot.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Address;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.AddressService;
import com.springboot.ecommerce.service.SellerService;
import com.springboot.ecommerce.service.UserService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/signup")
	public Seller insertSeller(@RequestBody Seller seller ) { // method is mapped to the url
		User user = seller.getUser();
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		userService.postUser(user);
		/* saving address with id */
		Address address = seller.getAddress();
		addressService.insert(address); // customer info as an object and give it to the repository via service
		return sellerService.insert(seller);

	}


	@GetMapping("/all") 
	public List<Seller> getAllSeller() { 
		List<Seller> list = sellerService.getAllSeller();
		return list;
	}
	
	@DeleteMapping("/delete/{id}")    
	public ResponseEntity<?> deleteSeller(@PathVariable("id") int id) {
		
		try {
			//validate id
			Seller seller = sellerService.getSellerById(id);
			//delete
			sellerService.deleteSeller(seller);
			return ResponseEntity.ok().body(" deleted successfully");
         } catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}


	
	
	
		

