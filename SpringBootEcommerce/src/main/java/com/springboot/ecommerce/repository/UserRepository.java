package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
