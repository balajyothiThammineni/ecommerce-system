package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.model.Executive;

public interface ExecutiveRepository extends JpaRepository <Executive,Integer> {

}
