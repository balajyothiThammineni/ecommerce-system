package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Review;
import com.springboot.ecommerce.repository.ReviewRepository;


@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	public void insert(Review review) {
	  reviewRepository.save(review);
		
	}

	public Review getById(int pid) throws InvalidIdException {
		Optional<Review> optional = reviewRepository.findById(pid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Product ID invalid");
		}
		return optional.get();
	}

	public void deleteReview(Review review) {
		reviewRepository.delete(review);
		
	}

	public Page<Review> getAllReviews(Pageable pageable) {
		return reviewRepository.findAll(pageable);
	}

	public List<Review> getAll() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();	
	}

	public Review getReview(int pid) throws InvalidIdException {
			Optional<Review> optional = reviewRepository.findById(pid);
			if (!optional.isPresent()) {
				throw new InvalidIdException("Product ID invalid");
			}
			return optional.get();
		}

	public List<Review> getByProductId(int pid) {
	
		return reviewRepository.getByProductId(pid);
	}

	public List<Review> getAllReviewsByCustomerId(int customerId){
		return reviewRepository.getReviewsByCustomerCustomerId(customerId);
	}

	

	

	

}
