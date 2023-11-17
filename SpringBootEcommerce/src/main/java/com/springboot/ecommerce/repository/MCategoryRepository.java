package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.model.MCategory;


public interface MCategoryRepository  extends JpaRepository <MCategory,Integer> {

}
