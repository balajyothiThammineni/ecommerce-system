package com.springboot.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

	Optional<Seller> getSellerByUserId(int userId);

	boolean existsByEmail(String email);


}
