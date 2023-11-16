package com.springboot.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.repository.ProductRepository;



@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product postProduct(Product product) {
		return productRepository.save(product);
	}
	

}
