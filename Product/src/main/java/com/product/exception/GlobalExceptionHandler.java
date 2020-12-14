package com.product.exception;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.model.Message;
import com.product.model.Msg;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductIdNotFoundException.class)
	public ResponseEntity<Message> productIdNotFoundExceptionHandler(ProductIdNotFoundException ex) {
		Message msg=new Message(HttpStatus.NOT_FOUND.value(),
		        new Date(),
		        ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNameNotFoundException.class)
	public ResponseEntity<Message> productNameNotFoundExceptionHandler(ProductNameNotFoundException ex) {
		Message msg=new Message(HttpStatus.NOT_FOUND.value(),
		        new Date(),
		        ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RatingNotValidException.class)
	public ResponseEntity<Message> ratingNotValidExceptionHandler(RatingNotValidException ex) {
		Message msg=new Message(HttpStatus.NOT_FOUND.value(),
		        new Date(),
		        ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidUser.class)
	public ResponseEntity<Message> invalidUserExceptionHandler(InvalidUser exp) {
		Message msg1=new Message(HttpStatus.NOT_FOUND.value(),
		        new Date(),
		        exp.getMessage());
		return new ResponseEntity<Message>(msg1, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> methodArgumentMismatchException(NumberFormatException numberFormatException)
	{
		return ResponseEntity.badRequest().body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now()
				, "Enter valid data"));
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> missingHeaderExceptionHandler(MissingRequestHeaderException ex) {
		return ResponseEntity.badRequest().body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now()
				, "Authorisation required"));
	}
	
	@ExceptionHandler(ConnectException.class)
	  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	  public ResponseEntity<?> connectionExceptionHandler(ConnectException ex) { 
		  return ResponseEntity.badRequest().body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now() , "Service is down"));
	}

}
