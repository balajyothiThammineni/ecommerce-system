package com.springboot.ecommerce.controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.ecommerce.dto.NewProductDTO;
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
	
	

	    @PostMapping(path = "/product/add/{sid}/{cid}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> addProduct(@PathVariable("sid") int sid, @PathVariable("cid") int cid,
	                                        @RequestBody NewProductDTO productDTO) throws IOException {
	        Product product = new Product();
	        try {
	            Seller seller = sellerService.getById(sid);
	            Category category = categoryService.getByid(cid);

	            product.setName(productDTO.getName());
	            product.setProductDescription(productDTO.getProductDescription());
	            product.setColour(productDTO.getColour());
	            product.setSize(productDTO.getSize());
	            product.setPrice(Long.parseLong(productDTO.getPrice()));
	            product.setStock(Integer.parseInt(productDTO.getStock()));
	            product.setSeller(seller);
	            product.setCategory(category);
	            product.setImageData(productDTO.getImage());
	            
	            // Set the featured property
	            product.setFeatured(productDTO.isFeatured());

	            product = productService.insert(product);
	            return ResponseEntity.ok().body(product);
	        } catch (InvalidIdException e) {
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
	
//	@GetMapping("/featured/all")
//	public List<Product> getFeauteredProducts(){
//		return productService.getAll();
//		
//	}
	
	 
	 @DeleteMapping("/product/delete/{productId}/{sellerId}")
	 public ResponseEntity<?>deleteSellerProduct(@PathVariable("productId") int productId, @PathVariable("sellerId") int sellerId){
		 try {
			 productService.deleteProductByProductIdAndSellerID(productId,sellerId);
			 
		 }catch (Exception e){
			 return ResponseEntity.badRequest().body(e.getMessage());
			 
		 }
		 return ResponseEntity.ok().body("order deleted sucessfully");
	 }
	 

	

	@GetMapping("/search/{qStr}")
	public List<Product> searchProductByName(@PathVariable("qStr") String qStr) {
		List<Product> list= productService.searchProductByName(qStr);
		return list; 
	}
	
	@GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) {
        Product pp = productService.getById(id);
        if (pp != null) {
            try {
                Resource resource = new UrlResource("file:D:/fileUpload/" + pp.getImageData());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
           
            
        }
	
        return ResponseEntity.notFound().build();

	}
	
	 @DeleteMapping("/product/delete/{pid}")
	    public ResponseEntity<?> deleteProduct(@PathVariable("pid") int id) {
	        try {
	            // Validate id
	            Product product = productService.getProductById(id);
	            
	            // Delete the product
	            productService.deleteProduct(product);
	            
	            return ResponseEntity.ok().body("Product deleted successfully");
	        } catch (InvalidIdException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	 @GetMapping("/products/{productId}")
		public Product getProductById(@PathVariable("productId") String productId){
			return productService.getProducts(Integer.parseInt(productId));
		}
		
	 @GetMapping("/featured/all")
	 public ResponseEntity<List<Product>> getFeaturedProducts() {
	     try {
	         List<Product> featuredProducts = productService.getFeaturedProducts();
	         return ResponseEntity.ok().body(featuredProducts);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	     }
	 }

	
	



	
}


     

