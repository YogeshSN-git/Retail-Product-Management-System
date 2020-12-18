package com.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.auth.model.AuthResponse;
import com.auth.model.UserData;

public interface CustomerDetailsService extends UserDetailsService {
	/**
	 * Checks login credentials by retrieving the user data from database for the
	 * given user ID. If the give user ID and password matches the user ID and
	 * password in the database then token is generated. Else UnauthorizedException
	 * is thrown.
	 * 
	 * @param userlogincredentials - User ID,Password
	 * @return User ID and generated token
	 */
	public UserData login(@RequestBody UserData userlogincredentials);

	/**
	 * Sets token validity by checking the expiry time for the given token. If the
	 * token is not expired validity is set to true. Else validity is set to false.
	 * 
	 * @param token JWT token to validate user
	 * @return User ID,User Name and Validity of the token.
	 */
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);

	/**
	 * Inspects the health status of a Spring Boot application
	 * 
	 * @return if service is running then HttpStatus.OK is returned
	 */
	public ResponseEntity<?> healthCheckup();
}
