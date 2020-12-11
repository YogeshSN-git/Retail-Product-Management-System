package com.vendor.entity;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

	Date timeStamp;
	String message;
	HttpStatus status;

	public MessageResponse(String message, HttpStatus status) {
		super();
		this.timeStamp = new Date();
		this.message = message;
		this.status = status;
	}

}
