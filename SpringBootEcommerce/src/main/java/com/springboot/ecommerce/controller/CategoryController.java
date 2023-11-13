package com.springboot.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category/add")
	public Category insertCategory(@RequestBody Category category) {
		return categoryService.insert(category);
	}
	
	@GetMapping("/category/getall")
    public List<Category> getAllCategoryr(){
		return categoryService.getAll();
	}
	

}
