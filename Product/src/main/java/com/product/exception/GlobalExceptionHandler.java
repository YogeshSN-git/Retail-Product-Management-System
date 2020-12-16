package com.product.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.NonUniqueResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.model.Message;
import com.product.model.Msg;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<Message> handleSocketTimeoutException(SocketTimeoutException ex) {
		log.error(ex.getMessage());
		Message msg = new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(FeignException.InternalServerError.class)
	public ResponseEntity<Message> handleFeignInternalServerError(FeignException ex) {
		log.error("Internal Server Error");
		Message msg = new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), "Internal Server Error");
		return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ProductIdNotFoundException.class)
	public ResponseEntity<Message> productIdNotFoundExceptionHandler(ProductIdNotFoundException ex) {
		log.error(ex.getMessage());
		Message msg = new Message(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNameNotFoundException.class)
	public ResponseEntity<Message> productNameNotFoundExceptionHandler(ProductNameNotFoundException ex) {
		log.error(ex.getMessage());
		Message msg = new Message(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RatingNotValidException.class)
	public ResponseEntity<Message> ratingNotValidExceptionHandler(RatingNotValidException ex) {
		log.error(ex.getMessage());
		Message msg = new Message(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());
		return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUser.class)
	public ResponseEntity<Message> invalidUserExceptionHandler(InvalidUser exp) {
		log.error(exp.getMessage());
		Message msg1 = new Message(HttpStatus.NOT_FOUND.value(), new Date(), exp.getMessage());
		return new ResponseEntity<Message>(msg1, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> methodArgumentMismatchException(NumberFormatException numberFormatException) {
		return ResponseEntity.badRequest()
				.body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Enter valid data"));
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> missingHeaderExceptionHandler(MissingRequestHeaderException ex) {
		return ResponseEntity.badRequest()
				.body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Authorisation required"));
	}

	@ExceptionHandler(ConnectException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> connectionExceptionHandler(ConnectException ex) {
		log.info(ex.getMessage());
		return ResponseEntity.badRequest()
				.body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Service is down"));
	}

	@ExceptionHandler(NonUniqueResultException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> nonUniqueResultExceptionHandler(NonUniqueResultException ex) {
		return ResponseEntity.unprocessableEntity()
				.body(new Msg(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Enter a unique product name"));
	}

}
