package com.springboot.ecommerce.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

public class OrderDto {
	private int orderId;
	private double totalPrice;
	private int quantity;
//	@CreationTimestamp
//	private LocalDateTime dateOfPurchase;
//	private String orderStatus="ordered";

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalprice() {
		return totalPrice;
	}

	public void setTotalprice(double totalprice) {
		this.totalPrice = totalprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

//	public LocalDateTime getDateOfPurchase() {
//		return dateOfPurchase;
//	}
//
//	public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
//		this.dateOfPurchase = dateOfPurchase;
//	}
//
//	public String getOrderStatus() {
//		return orderStatus;
//	}

//	public void setOrderStatus(String orderStatus) {
//		this.orderStatus = orderStatus;
//	}

}
