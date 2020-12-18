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

	/**
	 * @param ex exception object to get SocketTimeoutException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<MessageResponse> handleSocketTimeoutException(SocketTimeoutException ex) {
		log.error(ex.getMessage());

		MessageResponse msg = new MessageResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param ex exception object to get FeignException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(FeignException.InternalServerError.class)
	public ResponseEntity<MessageResponse> handleFeignInternalServerError(FeignException ex) {
		log.error("Internal Server Error");

		MessageResponse msg = new MessageResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles OutOfStockException
	 * 
	 * @param ex exception object to get OutOfStockException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<MessageResponse> handleOutOfStockException(OutOfStockException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.NOT_FOUND));
	}

	/**
	 * @param ex exception object to get UnauthorizedException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<MessageResponse> handleUnauthorizedExceptions(UnauthorizedException ex) {

		log.error("Unauthorized request");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Unauthorized request. Login again...", HttpStatus.UNAUTHORIZED));
	}

	/**
	 * @param ex exception object to get ProductItemNotFoundException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductItemNotFoundException.class)
	public ResponseEntity<MessageResponse> handleProductItemNotFoundException(ProductItemNotFoundException ex) {
		log.error("Product Id not found");
		return ResponseEntity.badRequest().body(new MessageResponse("Product Id not found", HttpStatus.NOT_FOUND));
	}

	/**
	 * @param ex exception object to get FeignException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.BadRequest.class)
	public ResponseEntity<?> handleFeignBadRequestExceptions(FeignException ex) {

		log.error("Feign Exception");
		return ResponseEntity.badRequest().body(new MessageResponse("Feign Exception", HttpStatus.BAD_REQUEST));
	}

	/**
	 * @param ex exception object to get ConnectException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleConnectExceptions(ConnectException ex) {

		log.error("Client service down");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Client service down", HttpStatus.SERVICE_UNAVAILABLE));
	}

	/**
	 * @param ex exception object to get MissingRequestHeaderException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {

		log.error("Required Bearer token");
		return ResponseEntity.badRequest().body(new MessageResponse("Required Bearer token", HttpStatus.UNAUTHORIZED));
	}

}
