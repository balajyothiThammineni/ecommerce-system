package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.model.MCategory;
import com.springboot.ecommerce.repository.MCategoryRepository;

@Service
public class MCategoryService {
	
	@Autowired
	private MCategoryRepository mCategoryRepository;


	public MCategory postmCategory(MCategory mCategory) {
		return mCategoryRepository.save(mCategory);
	}

        public List<MCategory> getAll() {
		return mCategoryRepository.findAll();
	}
        
        public MCategory getMCategoryById(int id) throws InvalidIdException{
        	Optional<MCategory> optional = mCategoryRepository.findById(id);
        	if(!optional.isPresent())
        		throw new InvalidIdException(" Id Invalid");
            MCategory mCategory = optional.get();
        	return mCategory;
        }
        public void deleteMcategory(MCategory mcategory) {
        	mCategoryRepository.delete(mcategory);
        }

		public MCategory getById(int mcid) {
			// TODO Auto-generated method stub
			return null;
		}

		



}
