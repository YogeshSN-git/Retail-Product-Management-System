package com.vendor.exception;

public class OutOfStockException extends RuntimeException {

	public OutOfStockException() {
		super("Product Out of Stock");
	}
}
