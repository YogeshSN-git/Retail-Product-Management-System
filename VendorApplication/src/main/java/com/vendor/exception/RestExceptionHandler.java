package com.vendor.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vendor.entity.MessageResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
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

		log.error("Unauthorized request");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Unauthorized request. Login again...", HttpStatus.UNAUTHORIZED));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ProductItemNotFoundException.class)
	public ResponseEntity<MessageResponse> handleProductItemNotFoundException(ProductItemNotFoundException ex) {
		log.error("Product Id not found");
		return ResponseEntity.badRequest().body(new MessageResponse("Product Id not found", HttpStatus.BAD_REQUEST));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(VendorNotFoundException.class)
	public ResponseEntity<MessageResponse> handleVendorNotFoundException(VendorNotFoundException ex) {
		log.error("Vendor not found");
		return ResponseEntity.badRequest().body(new MessageResponse("Vendor not found", HttpStatus.BAD_REQUEST));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.BadRequest.class)
	public ResponseEntity<?> handleFeignBadRequestExceptions(FeignException ex) {

		log.error("Feign Exception");
		return ResponseEntity.badRequest().body(new MessageResponse("Feign Exception", HttpStatus.BAD_REQUEST));
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

}
