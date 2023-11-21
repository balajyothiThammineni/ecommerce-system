package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{

}
