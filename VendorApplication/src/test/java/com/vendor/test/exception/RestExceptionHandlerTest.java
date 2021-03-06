package com.vendor.test.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MissingRequestHeaderException;

import com.vendor.exception.OutOfStockException;
import com.vendor.exception.ProductItemNotFoundException;
import com.vendor.exception.RestExceptionHandler;
import com.vendor.exception.UnauthorizedException;

import feign.FeignException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RestExceptionHandlerTest {
	@InjectMocks
	RestExceptionHandler restExceptionHandler;
	FeignException feignException;

	@Test
	public void handleUnauthorizedExceptions() {
		assertEquals(
				restExceptionHandler.handleUnauthorizedExceptions(new UnauthorizedException(null)).getStatusCodeValue(),
				400);
	}
	@Test
	public void handleOutOfStockExceptions() {
		assertEquals(
				restExceptionHandler.handleOutOfStockException(new OutOfStockException()).getStatusCodeValue(),
				400);
	}
	@Test
	public void handleProductItemNotFoundExceptions() {
		assertEquals(restExceptionHandler.handleProductItemNotFoundException(new ProductItemNotFoundException(null))
				.getStatusCodeValue(), 400);
	}

	@Test
	public void handleFeignException() {
		assertEquals(restExceptionHandler.handleFeignBadRequestExceptions(feignException).getStatusCodeValue(), 400);
	}

	@Test
	public void handleConnectExceptions() {
		assertEquals(restExceptionHandler.handleConnectExceptions(new ConnectException(null)).getStatusCodeValue(),
				400);
	}

	@Test
	public void handleMissingRequestHeaderExceptions() {
		assertEquals(
				restExceptionHandler.handleMissingRequestHeaderException(new MissingRequestHeaderException(null, null))
						.getStatusCodeValue(),
				400);
	}

	@Test
	public void handleSocketTimeoutException() {
		assertEquals(restExceptionHandler.handleSocketTimeoutException(new SocketTimeoutException("Connect timed out"))
				.getStatusCodeValue(), 500);
	}

	@Test
	public void handleFeignInternalServerError() {
		assertEquals(restExceptionHandler.handleFeignInternalServerError(feignException).getStatusCodeValue(), 500);
	}
}
