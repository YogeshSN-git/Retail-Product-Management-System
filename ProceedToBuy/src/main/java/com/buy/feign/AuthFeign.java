package com.buy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buy.response.AuthResponse;

@FeignClient(url = "${auth.feign.url}", name = "authapp")
public interface AuthFeign {

	/**
	 * Calling Authorization microservice for token validation using FeignClient
	 * 
	 * @param token JWT token to validate user
	 * @return {@code AuthResponse} containing token validity
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);
}
