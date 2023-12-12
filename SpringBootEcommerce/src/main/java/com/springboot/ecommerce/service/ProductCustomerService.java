package com.springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.model.ProductCustomer;
import com.springboot.ecommerce.repository.ProductCustomerRepository;
import com.springboot.ecommerce.repository.ProductRepository;

@Service
public class ProductCustomerService {

	
	
	@Autowired
	private ProductCustomerRepository productCustomerRepository;
	
	public List<ProductCustomer> insert(List<ProductCustomer> ProductCustomer) {
		return productCustomerRepository.saveAll(ProductCustomer);

	}

	public List<ProductCustomer> getMyOrders(int cid) {
		return productCustomerRepository.getMyOrders(cid);
	}
//
//	public void deleteCustomer(ProductCustomer productCustomer) {
//		productCustomerRepository.delete(productCustomer);
//		
//	}


	   public void deleteOrder (int Orderid ){
		 productCustomerRepository.deleteById(Orderid);
		
	}

}