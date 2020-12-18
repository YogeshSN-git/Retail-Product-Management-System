package com.buy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.buy.entity.ProductItem;

@FeignClient(url = "${product.feign.url}", name = "product")
public interface ProductFeign {

	/**
	 * Calling Product microservice to fetch ProductItem using Feign
	 * 
	 * @param token JWT token to validate user
	 * @param id    ProductId
	 * @return {@code ProductItem} with id
	 */

	@GetMapping("/searchProductById/{id}")
	public ProductItem searchProductById(@RequestHeader("Authorization") String token, @PathVariable("id") int id);

}
