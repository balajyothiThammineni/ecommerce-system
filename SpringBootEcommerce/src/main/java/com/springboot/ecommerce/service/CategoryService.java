package com.springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.repository.CategoryRepository;



@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category insert(Category category) {
		return categoryRepository.save(category);
	}
	
public List<Category> getAll() {
		
		return categoryRepository.findAll();	}



}
