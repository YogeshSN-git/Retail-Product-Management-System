package com.auth.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.controller.AuthController;
import com.auth.controller.UnauthorizedException;
import com.auth.dao.UserDAO;
import com.auth.model.AuthResponse;
import com.auth.model.UserData;
import com.auth.service.CustomerDetailsService;
import com.auth.service.JwtUtil;

@SpringBootTest(classes = AuthControllerTest.class)
@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

	@InjectMocks
	AuthController authController;

	AuthResponse authResponse;

	UserDetails userdetails;

	@Mock
	JwtUtil jwtutil;

	@Mock
	CustomerDetailsService custdetailservice;

	@Mock
	UserDAO userservice;

	@Test
	public void loginTest() {

		UserData user = new UserData("admin", "admin", null, null);
		UserDetails loadUserByUsername = custdetailservice.loadUserByUsername("admin");
		UserDetails value = new User(user.getUserid(), user.getUpassword(), new ArrayList<>());
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		UserData login = authController.login(user);
		assertEquals(login.getUserid(),user.getUserid());
	}

	@Test(expected = UnauthorizedException.class)
	public void loginTestFailed() {

		UserData user = new UserData("admin", "admin", null, null);
		UserDetails loadUserByUsername = custdetailservice.loadUserByUsername("admin");
		UserDetails value = new User(user.getUserid(), "admin11", new ArrayList<>());
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		authController.login(user);
	}

	@Test
	public void validateTestValidtoken() {

		// when(authController.verifyToken("token")).thenReturn(new
		// AuthResponse("admin", "admin", true))
		when(jwtutil.validateToken("bearer token")).thenReturn(true);
		when(jwtutil.extractUsername("bearer token")).thenReturn("admin");
		UserData user1 = new UserData("admin", "admin", "admin", null);
		Optional<UserData> data = Optional.of(user1);
		when(userservice.findById("admin")).thenReturn(data);
		AuthResponse validity = authController.getValidity("bearer token");
		assertEquals(true, validity.isValid());

	}

	@Test
	public void validateTestInValidtoken() {

		// when(authController.verifyToken("token")).thenReturn(new
		// AuthResponse("admin", "admin", true))
		when(jwtutil.validateToken("bearer token")).thenReturn(false);
		AuthResponse validity = authController.getValidity("bearer token");
		assertEquals(validity.isValid(), false);
	}

	@Test
	public void testhealth() {
		ResponseEntity<?> healthCheckup = authController.healthCheckup();
		assertEquals(healthCheckup.getStatusCodeValue(), 200);

	}

}
