package com.springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.ecommerce.dto.ProductDto;
import com.springboot.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    @Query(value = "select p.product_id ,p.colour ,p.price ,p.product_description , p.product_name  ,p.size ,p.stock ,p.category_category_id , seller_seller_id   \r\n" + 
    		"from product p ,category c  where p.category_category_id = c.category_id and c.category_id =:cid",nativeQuery = true)
	List<ProductDto> findByCategoryId(int cid);

	@Query(value="select p.product_id ,p.colour ,p.price ,p.product_description , p.product_name  ,p.size ,p.stock ,p.category_category_id ,"
			+ " seller_seller_id from product p, seller s where p.seller_seller_id=s.seller_id and s.seller_id=:sid",nativeQuery=true)
	List<ProductDto> findBySellerId(int sid);
}
  