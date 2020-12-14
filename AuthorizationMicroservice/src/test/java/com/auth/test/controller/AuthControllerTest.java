package com.auth.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.auth.controller.AuthController;
import com.auth.model.AuthResponse;
import com.auth.model.UserData;
import com.auth.service.CustomerDetailsServiceImpl;

@SpringBootTest
public class AuthControllerTest {
	@InjectMocks
	AuthController authController;
	@Mock
	CustomerDetailsServiceImpl customerDetailsService;
	@Test
	void testHealthCheck()
	{
		assertEquals(authController.healthCheckup(),customerDetailsService.healthCheckup());
	}
	@Test
	void loginTest()
	{
		UserData user1 = new UserData("admin", "admin", "admin",null);
		when(customerDetailsService.login(user1)).thenReturn(user1);
		assertEquals(authController.login(user1),user1);
	}
	@Test
	void getValidityTest()
	{
		 AuthResponse auth = new AuthResponse("admin","admin",true); 
		 when(customerDetailsService.getValidity("token")).thenReturn(auth);
		 assertEquals(authController.getValidity("token"),auth);
	}
}
