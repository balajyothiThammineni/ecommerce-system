package com.springboot.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	
	
	@Query(value = "SELECT c.* FROM Customer c, Product p, Product_Customer pc " +
	        "WHERE p.product_id = pc.product_product_id " +
	        "AND c.customer_id = pc.customer_customer_id " +
	        "AND p.seller_seller_id = :sellerId", nativeQuery = true)
	List<Customer> getCustomerBySeller(@Param("sellerId") int sellerId);

	

	boolean existsByCustomerEmail(String email);



	Optional<Customer> getCustomerByUserId(int customerId);



	



	










	


	
	

    



}
