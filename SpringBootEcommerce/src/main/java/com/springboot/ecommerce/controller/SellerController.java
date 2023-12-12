package com.springboot.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/signup")
	public Seller addSeller(@RequestBody Seller seller) {

		User user = seller.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.SELLER);
		user = userService.insert(user);
		seller.setUser(user);
		Address address = addressService.postAddress(seller.getAddress());
		seller.setAddress(address);
		return sellerService.insert(seller);
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
}
