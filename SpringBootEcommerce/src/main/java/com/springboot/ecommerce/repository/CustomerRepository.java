package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.ecommerce.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
