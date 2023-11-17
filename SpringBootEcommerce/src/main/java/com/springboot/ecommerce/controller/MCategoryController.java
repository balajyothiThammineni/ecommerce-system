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
import com.springboot.ecommerce.model.Executive;
import com.springboot.ecommerce.model.MCategory;
import com.springboot.ecommerce.service.ExecutiveService;
import com.springboot.ecommerce.service.MCategoryService;

@RestController
public class MCategoryController {
	@Autowired
	private MCategoryService mcategoryService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/mcategory/add/{eid}")
	public ResponseEntity<?>postMCategory(@PathVariable("eid") int eid,
			@RequestBody MCategory mCategory) {
		try {
			Executive executive = executiveService.getById(eid);
			mCategory.setExecutive(executive);
			mCategory= mcategoryService.postmCategory(mCategory);
			return ResponseEntity.ok().body(mCategory);
		}catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
      }
    }	
	
	@GetMapping("/mcategory/all")
    public List<MCategory> getAllMcategory(){
		return mcategoryService.getAll();
	}
	
	@DeleteMapping("/mcategory/delete/{cid}") 
	public ResponseEntity<?> deleteMcategory(@PathVariable("cid") int id) {

		try {
			MCategory mcategory = mcategoryService.getMCategoryById(id);
			// delete
			mcategoryService.deleteMcategory(mcategory);
			return ResponseEntity.ok().body(" mainCategory deleted successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
	
}



