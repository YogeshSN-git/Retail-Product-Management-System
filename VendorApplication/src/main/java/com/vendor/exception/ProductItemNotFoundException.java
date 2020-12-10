package com.vendor.exception;

public class ProductItemNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ProductItemNotFoundException(String message)
	{
		super(message);
	}
}
