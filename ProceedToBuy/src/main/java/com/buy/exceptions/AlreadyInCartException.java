package com.buy.exceptions;

public class AlreadyInCartException extends RuntimeException {

	public AlreadyInCartException() {
		super("Product already in cart");
	}
}
