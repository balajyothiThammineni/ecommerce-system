package com.springboot.ecommerce.controller;

import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.SellerDto;
import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Address;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.model.User;
import com.springboot.ecommerce.service.AddressService;
import com.springboot.ecommerce.service.SellerService;
import com.springboot.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
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
	@Autowired
	private Logger logger;
	

	@PostMapping("/signup")
	public ResponseEntity<?> addSeller(@RequestBody Seller seller) {
	    // Check if the email already exists in the Seller table
	    String email = seller.getEmail();
	    if (sellerService.existsByEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }


	    User user = seller.getUser();
	    String password = user.getPassword();
	    String encodedPassword = passwordEncoder.encode(password);
	    user.setPassword(encodedPassword);
	    user.setRole(Role.SELLER);
	    user = userService.insert(user);
	    seller.setUser(user);

	    // Assuming you have an address associated with the seller
	    Address address = addressService.postAddress(seller.getAddress());
	    seller.setAddress(address);

	    // Insert the seller into the database
	    Seller insertedSeller = sellerService.insert(seller);
	    
	    logger.info("Seller signed up: {}", insertedSeller.getSellerName());

	    // Return a success message along with the created seller
	    return ResponseEntity.status(HttpStatus.OK).body(insertedSeller);
	}


	@GetMapping("/getone/{sid}")
	public ResponseEntity<?> getExecutive(@PathVariable("sid") int id) {

		try {
			Seller seller = sellerService.getSellerById(id);
			return ResponseEntity.ok().body(seller);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/view/all")
	public List<Seller> getAllSeller() {
		List<Seller> list = sellerService.getAllSeller();
		return list;
	}

	
	@DeleteMapping("/delete/{sid}")
	public ResponseEntity<?> deleteSeller(@PathVariable("sid") int id) {

		try {
			// validate id
			Seller seller = sellerService.getSellerById(id);
			// delete
			sellerService.deleteSeller(seller);
			return ResponseEntity.ok().body(" deleted successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateSeller(@PathVariable("id") int id, @RequestBody SellerDto newSeller) {
		try {
			Seller oldSeller = sellerService.getOne(id);
			if (newSeller.getSellerName() != null)
				oldSeller.setSellerName(newSeller.getSellerName());
			if (newSeller.getEmail() != null)
				oldSeller.setEmail(newSeller.getEmail());
			if (newSeller.getNumber() != null)
				oldSeller.setNumber(newSeller.getNumber());
			if (newSeller.getGstin() != null)
				oldSeller.setGstin(newSeller.getGstin());
			if (newSeller.getHno() != null)
				oldSeller.setGstin(newSeller.getGstin());
			oldSeller = sellerService.postSeller(oldSeller);
			return ResponseEntity.ok().body(oldSeller);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@GetMapping("/findByUserId/{userId}")
	public ResponseEntity<?> getSellerByUserId(@PathVariable("userId") int userId) {

		try {
			Seller seller = sellerService.getSellerByUserId(userId);
			return ResponseEntity.ok().body(seller);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
