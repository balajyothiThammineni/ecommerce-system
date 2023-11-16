package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.service.ProductService;
import com.springboot.ecommerce.service.SellerService;

@RestController
public class ProductController {
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product/add/{sid}")
	public ResponseEntity<?> postProduct(@RequestBody Product product, 
						    @PathVariable("sid") int sid) {
			
		try {
			Seller seller = sellerService.getOne(sid);
			product.setSeller(seller);
			product = productService.postProduct(product);
			return ResponseEntity.ok().body(product);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
