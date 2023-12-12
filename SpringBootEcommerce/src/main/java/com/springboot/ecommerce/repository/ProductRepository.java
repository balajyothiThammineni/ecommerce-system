package com.springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.dto.ProductDto;
import com.springboot.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	
	@Query("select p from Product p where p.featured=?1")
	List<Product> getFeaturedProducts(String pName);


	@Query("select p from Product p where p.category.id=?1")
	List<Product> getProductsByCategoryId(int cid, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	List<Product> searchProductByName(String qStr);

    @Query("select p from Product p where p.seller.id =?1")
	List<Product> getBySellerId(int sid);





}
  