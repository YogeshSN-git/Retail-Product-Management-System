package com.buy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.buy.entity.Vendor;

@FeignClient(url = "${vendor.feign.url}", name = "vendor")
public interface VendorFeign {

	@GetMapping("/vendor/{productId}")
	public Vendor getVendorDetails(@PathVariable int productId,@RequestHeader("Authorization") String token) ;
}
