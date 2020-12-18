package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.AuthResponse;
import com.auth.model.UserData;
import com.auth.service.CustomerDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

	@Autowired
	private CustomerDetailsServiceImpl customerDetailsService;

	/**
	 * Inspects the health status of the Spring Boot application by calling the
	 * healthCheckup method in service class
	 * 
	 * @return if service is running then HttpStatus.OK is returned
	 */
	@RequestMapping(path = "/health", method = RequestMethod.GET)
	public ResponseEntity<?> healthCheckup() {
		return customerDetailsService.healthCheckup();
	}

	/**
	 * Checks login credentials by calling the login method in service class
	 * 
	 * @param userlogincredentials - User ID,Password
	 * @return token that is generated for given user ID
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserData login(@RequestBody UserData userlogincredentials) {
		return customerDetailsService.login(userlogincredentials);
	}

	/**
	 * Sets token validity by calling the getValidity method in service class
	 * 
	 * @param token JWT token to validate user
	 * @return User ID, Token Validity, User name for given User ID
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token) {
		return customerDetailsService.getValidity(token);
	}

}
