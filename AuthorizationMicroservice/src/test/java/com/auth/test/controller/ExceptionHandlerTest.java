package com.auth.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MissingRequestHeaderException;

import com.auth.controller.RestExceptionHandler;
import com.auth.controller.UnauthorizedException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerTest {

	@InjectMocks
	RestExceptionHandler restExceptionHandler;

	@Test
	public void handleMissingRequestHeaderException() {
		assertEquals(restExceptionHandler.handleMissingRequestHeaderException(new MissingRequestHeaderException("authorization", null)).getStatusCodeValue(), 400);
	}

	@Test
	public void handleUnauthorizedExceptions() {
		assertEquals(
				restExceptionHandler.handleUnauthorizedExceptions(new UnauthorizedException(null)).getStatusCodeValue(),
				400);
	}
}
