package com.buy.exceptions;

public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException() {
		super("Unauthorized request. Login again...");
	}
}
