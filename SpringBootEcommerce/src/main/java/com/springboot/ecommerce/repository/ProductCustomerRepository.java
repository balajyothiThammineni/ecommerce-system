package com.springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.ProductCustomer;
@Repository
public interface ProductCustomerRepository extends JpaRepository<ProductCustomer,Integer>{
	
	
   @Query("SELECT pc.dateOfPurchase, pc.quantity, pc.orderStatus, pc.amount FROM ProductCustomer pc WHERE pc.customer.customerId = :cid")
   List<ProductCustomer> getMyOrders(int cid);
     
}

