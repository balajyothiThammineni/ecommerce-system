package com.springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.ecommerce.dto.ProductDto;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.Review;
import com.springboot.ecommerce.repository.ProductRepository;



@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	

	public Product insert(Product product) {
		return productRepository.save(product);
	}


	public List<ProductDto> getByCategoryId(int id) {
		return productRepository.findByCategoryId(id);
	}


	public List<ProductDto> getBySellerId(int sid) {
		return productRepository.findBySellerId(sid);
	}


	public Product getById(int pid) {
		return productRepository.getById(pid);
	}


	public List<Product> getAll() {
		return productRepository.findAll();	
	}


	public List<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable).getContent();
	}


	public Product getProducts(int pid) {
	
		return productRepository.getById(pid) ;
	}




	


	

	






	
	
}
