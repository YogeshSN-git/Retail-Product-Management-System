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

	/**
	 * Handles SocketTimeoutException
	 * 
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
	 * Handles FeignException.InternalServerError
	 * 
	 * @param ex exception object to get FeignException.InternalServerError message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(FeignException.InternalServerError.class)
	public ResponseEntity<MessageResponse> handleFeignInternalServerError(FeignException ex) {
		log.error("Internal Server Error");

		MessageResponse msg = new MessageResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles FeignException.BadRequest
	 * 
	 * @param ex exception object to get FeignException.BadRequest message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(FeignException.BadRequest.class)
	public ResponseEntity<MessageResponse> handleFeignBadRequest(FeignException ex) {

		log.error("Product Out of Stock");

		MessageResponse msg = new MessageResponse("Product Out of Stock", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles FeignException.NotFound
	 * 
	 * @param ex exception object to get FeignException.NotFound message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(FeignException.NotFound.class)
	public ResponseEntity<MessageResponse> handleFeignNotFoundError(FeignException ex) {

		log.error("Product Not Found");

		MessageResponse msg = new MessageResponse("Product Not Found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles UnauthorizedException
	 * 
	 * @param ex exception object to get UnauthorizedException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<MessageResponse> handleUnauthorizedExceptions(UnauthorizedException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED));
	}

	/**
	 * Handles ConnectException
	 * 
	 * @param ex exception object to get ConnectException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleConnectExceptions(ConnectException ex) {

		log.error("Client service down");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Client service down", HttpStatus.SERVICE_UNAVAILABLE));
	}

	/**
	 * Handles MissingRequestHeaderException
	 * 
	 * @param ex exception object to get MissingRequestHeaderException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {

		log.error("Required Bearer token");
		return ResponseEntity.badRequest().body(new MessageResponse("Required Bearer token", HttpStatus.UNAUTHORIZED));
	}

	/**
	 * Handles AlreadyInWishlistException
	 * 
	 * @param ex exception object to get AlreadyInWishlistException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(AlreadyInWishlistException.class)
	public ResponseEntity<MessageResponse> handleAlreadyInWishlistException(AlreadyInWishlistException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}

	/**
	 * Handles AlreadyInCartException
	 * 
	 * @param ex exception object to get AlreadyInCartException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ExceptionHandler(AlreadyInCartException.class)
	public ResponseEntity<MessageResponse> handleAlreadyInCartException(AlreadyInCartException ex) {

		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}

	/**
	 * Handles NumberFormatException
	 * 
	 * @param ex exception object to get NumberFormatException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<MessageResponse> handleNumberFormatException(NumberFormatException ex) {

		log.error("Enter valid numbers");
		return ResponseEntity.badRequest().body(new MessageResponse("Enter valid numbers", HttpStatus.BAD_REQUEST));
	}

	/**
	 * Handles DateTimeException
	 * 
	 * @param ex exception object to get DateTimeException message
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<MessageResponse> handleDateTimeException(DateTimeException ex) {

		log.error("Enter valid Date");
		return ResponseEntity.badRequest().body(new MessageResponse("Enter valid Date", HttpStatus.BAD_REQUEST));
	}

}
