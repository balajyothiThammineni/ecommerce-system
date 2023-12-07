package com.springboot.ecommerce.controller;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.service.CategoryService;
import com.springboot.ecommerce.service.ProductService;
import com.springboot.ecommerce.service.SellerService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProductController {
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/product/add/{sid}/{cid}")
	public ResponseEntity<?> addProduct(@PathVariable("sid") int sid,
							  @PathVariable("cid") int cid,
							  @RequestBody Product product) {
		
		try {
		/* Step 1: go to DB and fetch sellerObject by seller id */
		Seller seller = sellerService.getById(sid);
		/* Step 2: go to DB and fetch categoryObject by category id */
		Category category = categoryService.getByid(cid); 
		/* Step 3: attach above object to Product object */
		product.setSeller(seller);
		product.setCategory(category);
		/* Step 4: Save product in DB */
		product = productService.insert(product);
		return ResponseEntity.ok().body(product);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	} 
	
	 
	@GetMapping("/category/all/{cid}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable("cid") int cid ,
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		/* validate category id. */
		try {
			Category category= categoryService.getById(cid);
			/* fetch products by category id with pagination */
			Pageable pageable=PageRequest.of(page, size);
			List<Product> list = productService.getProductsByCategoryId(cid,pageable);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/product/seller/{sid}")
	public ResponseEntity<?> getProductsBySeller(@PathVariable ("sid") int sid){
		try {
			Seller seller= sellerService.getOne(sid);
			List<Product> list=productService.getBySellerId(sid);
			return ResponseEntity.ok().body(list);
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/product/getall/{page}/{size}")
	public List<Product> getAllProducts(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return productService.getAllProducts(pageable);
	}

	@GetMapping("/product/getall")
    public List<Product> getAllProducts(){
		return productService.getAll();
	}
	
	@GetMapping("/featured/all")
	public List<Product> getFeauteredProducts(){
		return productService.getAll();
	}

	

	@GetMapping("/search/{qStr}")
	public List<Product> searchProductByName(@PathVariable("qStr") String qStr) {
		List<Product> list= productService.searchProductByName(qStr);
		return list; 
	}

}

     

