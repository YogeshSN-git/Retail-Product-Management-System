package com.buy.exceptions;

public class OutOfStockException extends RuntimeException {

	public OutOfStockException() {
		super("Product Out of Stock");
	}
}
