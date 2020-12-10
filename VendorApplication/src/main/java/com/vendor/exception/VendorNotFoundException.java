package com.vendor.exception;

public class VendorNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public VendorNotFoundException(String message)
	{
		super(message);
	}

}
