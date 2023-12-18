package com.springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{
	
	
	@Query("select r from Review r where r.product.id =?1")
	List<Review> getByProductId(int pid);

	List<Review> getReviewsByCustomerCustomerId(int customerId);

	

}
