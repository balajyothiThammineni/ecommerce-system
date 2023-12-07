package com.springboot.ecommerce.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.CategoryDto;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.service.CategoryService;
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/category/add") // adding a category
	public Category postCategory(@RequestBody Category category) { // method is mapped to a URL
        category  = categoryService.postCategory(category);
		return category;
	}
	
	
     
	@GetMapping("/category/getone/{id}")
	public ResponseEntity<?> getCategory(@PathVariable("id") int id) {
		try {
			Category category = categoryService.getCategory(id);
			return ResponseEntity.ok().body(category);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/category/delete/{cid}") /* 8080/customer/delete/13 */
	public ResponseEntity<?> deleteCategory(@PathVariable("cid") int id) {

		try {
			// validate id
			Category category = categoryService.getCategoryById(id);
			// delete
			categoryService.deleteCategory(category);
			return ResponseEntity.ok().body(" deleted successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping("/category/update/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody CategoryDto newCategory) {

		try {
			Category oldCategory = categoryService.getCategory(id);
			if (newCategory.getCategoryName() != null)
				oldCategory.setCategoryName(newCategory.getCategoryName());
			oldCategory = categoryService.postCategory(oldCategory);
			return ResponseEntity.ok().body(oldCategory);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@GetMapping("/category/getall")
    public List<Category> getAllCategory(){
		return categoryService.getAll();
	}
	
	}
	
	
		
			
		


	
		
	


