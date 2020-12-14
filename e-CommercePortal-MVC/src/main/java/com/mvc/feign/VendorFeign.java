package com.mvc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${vendor.feign.url}", name = "vendor")
public interface VendorFeign {

	@GetMapping("/productStock/{productId}")
	public int getProductStock(@PathVariable int productId, @RequestHeader("Authorization") String token);

}
