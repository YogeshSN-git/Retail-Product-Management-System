package com.vendor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendor.entity.AuthResponse;

@FeignClient(url = "${auth.feng.client}", name = "${auth.feng.name}")
public interface AuthClient {
	/**
	 * Calling Authorization microservice for token validation using FeignClient
	 * 
	 * @param token JWT token to validate user
	 * @return AuthResponse object
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);
}
