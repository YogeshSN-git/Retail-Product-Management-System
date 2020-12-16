package com.buy.exceptions;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.DateTimeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.buy.response.MessageResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<MessageResponse> handleSocketTimeoutException(SocketTimeoutException ex) {
		log.error(ex.getMessage());

		MessageResponse msg = new MessageResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(FeignException.InternalServerError.class)
	public ResponseEntity<MessageResponse> handleFeignInternalServerError(FeignException ex) {
		log.error("Internal Server Error");

		MessageResponse msg = new MessageResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<MessageResponse> handleUnauthorizedExceptions(UnauthorizedException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleConnectExceptions(ConnectException ex) {

		log.error("Client service down");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Client service down", HttpStatus.SERVICE_UNAVAILABLE));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {

		log.error("Required Bearer token");
		return ResponseEntity.badRequest().body(new MessageResponse("Required Bearer token", HttpStatus.UNAUTHORIZED));
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AlreadyInWishlistException.class)
	public ResponseEntity<MessageResponse> handleAlreadyInWishlistException(AlreadyInWishlistException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AlreadyInCartException.class)
	public ResponseEntity<MessageResponse> handleAlreadyInCartException(AlreadyInCartException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<MessageResponse> handleNumberFormatException(NumberFormatException ex) {

		log.error("Enter valid numbers");
		return ResponseEntity.badRequest().body(new MessageResponse("Enter valid numbers", HttpStatus.BAD_REQUEST));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<MessageResponse> handleDateTimeException(DateTimeException ex) {

		log.error("Enter valid Date");
		return ResponseEntity.badRequest().body(new MessageResponse("Enter valid Date", HttpStatus.BAD_REQUEST));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<MessageResponse> handleOutOfStockException(OutOfStockException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}
}
