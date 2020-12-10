package com.buy.model;

import java.util.Date;

public class MessageResponse {

	Date timeStamp;
	String message;
//	HttpStatus status;

	public MessageResponse(String message) {
		super();
		this.message = message;
		this.timeStamp = new Date();
	}

}
