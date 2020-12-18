package com.buy.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.buy.entity.Vendor;

@FeignClient(url = "${vendor.feign.url}", name = "vendor")
public interface VendorFeign {

	/**
	 * Calling Vendor Service to get list of vendor details
	 * 
	 * @param productId ProductId
	 * @param token     JWT token to validate user
	 * @return returns {@code List<Vendor>} selling ProductItem
	 */
	@GetMapping("/vendor/{productId}")
	public List<Vendor> getVendorDetails(@PathVariable int productId, @RequestHeader("Authorization") String token);
}
