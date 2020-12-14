package com.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.auth.model.AuthResponse;
import com.auth.model.UserData;

public interface CustomerDetailsService extends UserDetailsService {
	public UserData login(@RequestBody UserData userlogincredentials);

	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);

	public ResponseEntity<?> healthCheckup();
}
