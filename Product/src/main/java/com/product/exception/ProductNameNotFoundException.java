package com.product.exception;

public class ProductNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductNameNotFoundException(String message) 
	{
		super(message);
	}

}
