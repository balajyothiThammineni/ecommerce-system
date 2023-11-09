package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.ecommerce.model.ProductCustomer;

public interface ProductCustomerRepository extends JpaRepository<ProductCustomer,Integer>{

}
