package com.springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.ProductCustomer;
@Repository
public interface ProductCustomerRepository extends JpaRepository<ProductCustomer,Integer>{
	
	
//   @Query(value = "SELECT pc.date_of_Purchase, pc.quantity, pc.order_Status, pc.amount FROM Product_Customer pc "
//   		+ "WHERE pc.customer_customer_Id=46", nativeQuery = true)
//   List<ProductCustomer> getMyOrders(@Param("cid")int cid);
	
	 @Query("SELECT pc FROM ProductCustomer pc WHERE pc.customer.customerId = :customerId")
	    List<ProductCustomer>getMyOrders(@Param("customerId") int customerId);
	}
     


