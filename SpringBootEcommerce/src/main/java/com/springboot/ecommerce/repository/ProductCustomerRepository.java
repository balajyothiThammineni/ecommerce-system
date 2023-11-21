package com.springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.ProductCustomer;
@Repository
public interface ProductCustomerRepository extends JpaRepository<ProductCustomer,Integer>{
   @Query(value= "select date_of_purchase,quantity,order_status,amount from product_Customer where customer_customer_id=:cid",nativeQuery = true)
   List<ProductCustomer> getMyOrders(int cid);
     
}
