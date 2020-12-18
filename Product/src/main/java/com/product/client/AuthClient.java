package com.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.product.model.AuthResponse;

@FeignClient(url = "${auth.feng.client}", name = "${auth.feng.name}")
public interface AuthClient {

	/**
	 * Communicates with Authorization Microservice by enabling feign client
	 * 
	 * @param token JWT token for validation
	 * @return AuthResponse object(user Id,user name,validity of token)
	 */
	@GetMapping("/validate")
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);
}
