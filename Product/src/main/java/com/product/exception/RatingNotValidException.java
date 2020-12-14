package com.product.exception;

public class RatingNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RatingNotValidException(String message) 
	{
		super(message);
	}

}
