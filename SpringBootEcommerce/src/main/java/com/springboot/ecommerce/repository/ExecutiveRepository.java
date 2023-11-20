package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.model.Executive;
@Repository
public interface ExecutiveRepository extends JpaRepository <Executive,Integer> {

}
