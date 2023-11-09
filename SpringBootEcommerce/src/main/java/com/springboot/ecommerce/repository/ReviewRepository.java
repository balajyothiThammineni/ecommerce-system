package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.ecommerce.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>{

}
