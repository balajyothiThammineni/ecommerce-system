package com.springboot.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.OrderDto;
import com.springboot.ecommerce.exception.InvalidIdException;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.ProductCustomer;
import com.springboot.ecommerce.service.CustomerService;
import com.springboot.ecommerce.service.ProductCustomerService;
import com.springboot.ecommerce.service.ProductService;
import com.springboot.ecommerce.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProductCustomerController<Order> {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCustomerService productCustomerService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/order/saveall")
	public ResponseEntity<?> orderProducts(@RequestBody List<OrderDto> orderDtoList) {
	    System.out.println(orderDtoList);
	    
	    List<ProductCustomer> productCustomers = new ArrayList<>();
	    
	    for (OrderDto orderDto : orderDtoList) {
	        ProductCustomer productCustomer = getProductCustomer(orderDto);
	        productCustomers.add(productCustomer);
	    }
	    
	    // Assuming orderDtoList contains at least one order
	    int customerId = orderDtoList.get(0).getCid();
	    
	    try {
	        userService.sendEmailOnOrderPlaced(customerId);
	    } catch (InvalidIdException e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body("Error sending email: " + e.getMessage());
	    }
	    
	    return ResponseEntity.ok().body(productCustomerService.insert(productCustomers));
	}

	
	public ProductCustomer getProductCustomer(OrderDto orderDto) {
		
		Customer customer = customerService.getCustomer(orderDto.getCid());
		Product product = productService.getById(orderDto.getPid());
		double totalPrice = 0;
			ProductCustomer productCustomer = new ProductCustomer();
			productCustomer.setCustomer(customer);
			productCustomer.setProduct(product);
			productCustomer.setQuantity(orderDto.getQuantity());
			productCustomer.setAmount(orderDto.getTotalprice());
			productCustomer.setQuantity(orderDto.getQuantity());
		
		   totalPrice = orderDto.getQuantity()*(product.getPrice());
			productCustomer.setAmount(totalPrice); 
			 
			return productCustomer;
		
	}
	
     @DeleteMapping("/order/delete/{id}")
	public ResponseEntity<?> deleteorder(@PathVariable("id") int id) {
		try {

			productCustomerService.deleteOrder(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body("order deleted successfully");
	}
	
	
     @GetMapping("/orders/{cid}") 
 	public ResponseEntity<?> getOrders(@PathVariable("cid") int cid) {
 		List<ProductCustomer> listOfOrders=null;
			 listOfOrders=productCustomerService.getMyOrders(cid);
       return new ResponseEntity<List<ProductCustomer>>(listOfOrders,HttpStatus.OK);
 	}
     

     
     @GetMapping("/order/getall")
     public List<Order> getAllOrders(){
 		return (List<Order>) productService.getAll();
 	}
	
	
}	
	
	
	
	
	
	


