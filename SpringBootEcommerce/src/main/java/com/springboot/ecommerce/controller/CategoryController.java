
package com.springboot.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.model.Customer;
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
    public List<Category> getAllCategory(){
		return categoryService.getAll();
	}
	
	
	@DeleteMapping("/category/delete/{id}") /* 8080/customer/delete/13 */
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id) {

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
	
		
}
	


