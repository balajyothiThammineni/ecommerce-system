package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.repository.ProductRepository;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	

	public Product insert(Product product) {
		return productRepository.save(product);
	}



	public Product getById(int pid) {
		return productRepository.getById(pid); // Review
	}


	public List<Product> getAll() {
		return productRepository.findAll();	
	}


	public List<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable).getContent();
	}


	public Product getProducts(int pid) {
	
		return productRepository.getById(pid) ;
	}


	public List<Product> getProductsByCategoryId(int cid, Pageable pageable) {
	//	List<Product> products= new ArrayList<>();
		
		List<Product> product= productRepository.getProductsByCategoryId(cid,pageable);
		
//		for (Product product2 : product) {
//	   Product product3 = new Product();
//	   byte[] image =null;
//	   if(product2.getImageData()!=null) {
//		  image = ImageUtil.decompressImage(product2.getImageData());  
//	   }
//	   
//	   
//	   product3.setImageData(image);
//	   products.add(product3);
//	   
//		}
		return product;
		
	}



	  public List<Product> searchProductByName(String qStr) {
		
		return productRepository.searchProductByName(qStr);
	}



	public List<Product> getBySellerId(int sid) {
	
		return productRepository.getBySellerId(sid);
	}


	public Product getOne(int id) throws InvalidIdException {
		Optional<Product> optional =  productRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException(" ID Invalid");
		}
		return optional.get();
	}



	public Product getProductById(int id) throws InvalidIdException {
        Optional<Product> optional = productRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidIdException("Product ID is invalid");
        }
        return optional.get();
    }



	public void deleteProduct(Product product) {
		productRepository.delete(product);
	
		
	}



	public boolean deleteProductByProductIdAndSellerID(int productId, int sellerId){
		int isSuccess = productRepository.deleteProductByProductIdAndAndSeller_SellerId(productId, sellerId);
		System.out.println("Is Product Deleted Successfully " + isSuccess);
		if(isSuccess == 0){
			return false;
		}else{
			return true;
		}
	}



	public List<Product> getFeaturedProducts() {
	    // Assuming there is a method like findAllByFeaturedTrue in your repository
	    return productRepository.findAllByFeaturedTrue();
	}


	
}






	




	


	

	






	
	

