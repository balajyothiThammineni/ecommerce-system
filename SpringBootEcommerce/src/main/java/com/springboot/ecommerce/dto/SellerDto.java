package com.springboot.ecommerce.dto;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import com.springboot.ecommerce.model.User;

public class SellerDto {
	private  int sellerId;
	private String sellerName;
	private String email;
	private String number;
	@Column(nullable=false)
	private String gstin;
	private String hno;
	private String pincode;
    private String state;
    
   
    
	public int getSellerId() {
		return sellerId;
		
		
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    

}
