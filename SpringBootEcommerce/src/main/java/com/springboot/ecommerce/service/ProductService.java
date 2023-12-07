package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.dto.ProductDto;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.repository.ProductRepository;



@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	

	public Product insert(Product product) {
		return productRepository.save(product);
	}



	public Product getById(int pid) {
		return productRepository.getById(pid); // Review
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


	public List<Product> getProductsByCategoryId(int cid, Pageable pageable) {
		 
		return productRepository.getProductsByCategoryId(cid,pageable);
	}



	  public List<Product> searchProductByName(String qStr) {
		
		return productRepository.searchProductByName(qStr);
	}



	public List<Product> getBySellerId(int sid) {
	
		return productRepository.getBySellerId(sid);
	}


	public Product getOne(int id) throws InvalidIdException {
		Optional<Product> optional =  productRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException(" ID Invalid");
		}
		return optional.get();
	}

}





	




	


	

	






	
	

