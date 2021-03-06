package com.product.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.persistence.NonUniqueResultException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MissingRequestHeaderException;

import feign.FeignException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
	FeignException feignException;
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;

	@Test
	public void handleProductIdNotFoundExceptionHandler() {
		assertEquals(globalExceptionHandler.productIdNotFoundExceptionHandler(new ProductIdNotFoundException(null))
				.getStatusCodeValue(), 404);
	}

	@Test
	public void handleProductNameNotFoundExceptionHandler() {
		assertEquals(globalExceptionHandler.productNameNotFoundExceptionHandler(new ProductNameNotFoundException(null))
				.getStatusCodeValue(), 404);
	}

	@Test
	public void handleRatingNotValidExceptionHandler() {
		assertEquals(globalExceptionHandler.ratingNotValidExceptionHandler(new RatingNotValidException(null))
				.getStatusCodeValue(), 404);
	}

	@Test
	public void handleInvalidUserExceptionHandler() {
		assertEquals(globalExceptionHandler.invalidUserExceptionHandler(new InvalidUser(null)).getStatusCodeValue(),
				404);
	}

	@Test
	public void handleMethodArgumentMismatchException() {
		assertEquals(globalExceptionHandler.methodArgumentMismatchException(new NumberFormatException(null))
				.getStatusCodeValue(), 400);
	}

	@Test
	public void handleMissingHeaderExceptionHandler() {
		assertEquals(globalExceptionHandler.missingHeaderExceptionHandler(new MissingRequestHeaderException(null, null))
				.getStatusCodeValue(), 400);
	}

	@Test
	public void handleConnectionExceptionHandler() {
		assertEquals(globalExceptionHandler.connectionExceptionHandler(new ConnectException(null)).getStatusCodeValue(),
				400);
	}

	@Test
	public void handleNonUniqueResultExceptionHandler() {
		assertEquals(globalExceptionHandler.nonUniqueResultExceptionHandler(new NonUniqueResultException(null))
				.getStatusCodeValue(), 422);
	}

	@Test
	public void handleSocketTimeoutException() {
		assertEquals(globalExceptionHandler
				.handleSocketTimeoutException(new SocketTimeoutException("Connect timed out")).getStatusCodeValue(),
				500);
	}

	@Test
	public void handleFeignInternalServerError() {
		assertEquals(globalExceptionHandler.handleFeignInternalServerError(feignException).getStatusCodeValue(), 500);
	}

}