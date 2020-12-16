package com.buy.test.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.DateTimeException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MissingRequestHeaderException;

import com.buy.exceptions.AlreadyInCartException;
import com.buy.exceptions.AlreadyInWishlistException;
import com.buy.exceptions.OutOfStockException;
import com.buy.exceptions.RestExceptionHandler;
import com.buy.exceptions.UnauthorizedException;

import feign.FeignException;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class RestExceptionHandlerTest {
	@InjectMocks
	RestExceptionHandler restExceptionHandler;
	// @Autowired
	FeignException feignException;

	@Test
	public void handleUnauthorizedExceptions() {
		assertEquals(
				restExceptionHandler.handleUnauthorizedExceptions(new UnauthorizedException()).getStatusCodeValue(),
				400);
	}

	@Test
	public void handleAlreadyInCartException() {
		assertEquals(
				restExceptionHandler.handleAlreadyInCartException(new AlreadyInCartException()).getStatusCodeValue(),
				400);
	}

	@Test
	public void handleNumberFormatException() {
		assertEquals(restExceptionHandler.handleNumberFormatException(new NumberFormatException()).getStatusCodeValue(),
				400);
	}

	@Test
	public void handleDateTimeException() {
		assertEquals(restExceptionHandler.handleDateTimeException(new DateTimeException(null)).getStatusCodeValue(),
				400);
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
	public void handleAlreadyInWishlistException() {
		assertEquals(restExceptionHandler.handleAlreadyInWishlistException(new AlreadyInWishlistException())
				.getStatusCodeValue(), 400);
	}

	@Test
	public void handleOutOfStockException() {
		assertEquals(restExceptionHandler.handleOutOfStockException(new OutOfStockException()).getStatusCodeValue(),
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
