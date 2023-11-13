package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.ecommerce.model.Seller;


public interface SellerRepository extends JpaRepository<Seller,Integer> {


}
