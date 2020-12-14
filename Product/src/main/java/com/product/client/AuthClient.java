package com.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


import com.product.model.AuthResponse;



@FeignClient(url = "${auth.feng.client}", name = "${auth.feng.name}")
public interface AuthClient {

	@GetMapping("/validate")
	public AuthResponse getValidity(@RequestHeader("Authorization")final String token);
}
