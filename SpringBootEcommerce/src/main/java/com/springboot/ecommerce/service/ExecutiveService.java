package com.springboot.ecommerce.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Executive;
import com.springboot.ecommerce.repository.ExecutiveRepository;

@Service
public class ExecutiveService {
	
	 @Autowired
		private ExecutiveRepository executiveRepository;
		
		public Executive insert(Executive executive) {
			
			return executiveRepository.save(executive);
		}

		public Executive getExecutive(int id) throws InvalidIdException {
		Optional<Executive> optional = executiveRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Executive ID invalid");
		}
		return optional.get();

	}

}
