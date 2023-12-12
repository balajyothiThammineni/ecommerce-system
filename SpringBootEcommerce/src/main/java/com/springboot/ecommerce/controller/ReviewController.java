package com.springboot.ecommerce.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.Review;
import com.springboot.ecommerce.model.Seller;
import com.springboot.ecommerce.service.CustomerService;
import com.springboot.ecommerce.service.ProductService;
import com.springboot.ecommerce.service.ReviewService;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ReviewController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/review/{cid}/{pid}")
	public ResponseEntity<?> addReview(@PathVariable("cid") int cid, @PathVariable("pid") int pid,
			@RequestBody Review review) throws InvalidIdException {

		Customer customer = customerService.getCustomer(cid);
		Product product = productService.getById(pid);

		review.setCustomer(customer);
		review.setProduct(product);
		review.setDate(LocalDate.now());
		reviewService.insert(review);
		return ResponseEntity.ok().body(review);
	}
	
	
	@GetMapping("/review/product/{pid}")
	public ResponseEntity<?> getReviewsByProduct(@PathVariable ("pid") int pid){
		try {
			Product product= productService.getOne(pid);
			List<Review> list=reviewService.getByProductId(pid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		
//	@DeleteMapping("/review/delete/{id}")
//	public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) throws InvalidIdException {
//
//		Review review = reviewService.getById(id);
//
//		reviewService.deleteReview(review);
//		return ResponseEntity.ok().body("review deleted successfully");
//	}
	
	@GetMapping("/review/getall/{page}/{size}")
	public Page<Review> getAllProducts(
			@PathVariable(value="page",required = false) Integer page,
			@PathVariable(value="size",required = false) Integer size) {
		Pageable pageable =  PageRequest.of(page, size);
		return reviewService.getAllReviews(pageable);
	
	}
	@GetMapping("/review/getall")
    public List<Review> getAllReviews(){
		return reviewService.getAll();
	}
}

