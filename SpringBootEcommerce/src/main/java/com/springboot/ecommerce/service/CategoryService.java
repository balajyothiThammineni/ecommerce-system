package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category postCategory(Category category) {
		return categoryRepository.save(category);
	}
	

	public Category getByid(int cid) throws InvalidIdException {
		Optional<Category> optional=categoryRepository.findById(cid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Invalid Category given");
		}
		return optional.get();
	}

	public Category getById(int cid) throws InvalidIdException {
		Optional<Category> optional = categoryRepository.findById(cid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Category id Invalid");
		}
		return optional.get();
	}

	public Category getCategory(int id) throws InvalidIdException{
		Optional<Category> optional = categoryRepository.findById(id);
		if(!optional.isPresent()) {
			throw new InvalidIdException("category ID Invalid");
		}
		return optional.get();
	}

	public Category getCategoryById(int id) throws InvalidIdException{
		Optional<Category> optional = categoryRepository.findById(id);
		if(!optional.isPresent())
			throw new InvalidIdException(" Id Invalid");
	    Category category = optional.get();
		return category;
}

	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
		
	}


	public List<Category> getAll() {
	
		return categoryRepository.findAll();
	}

} 
