package com.springboot.ecommerce.exception;

public class InvalidNameException {



	private static final long serialVersionUID = -7611874505314550904L;

	private String message;

	public InvalidNameException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
	
	
}



