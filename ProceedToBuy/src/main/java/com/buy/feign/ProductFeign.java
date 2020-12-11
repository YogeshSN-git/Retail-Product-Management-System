package com.buy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.buy.entity.ProductItem;

@FeignClient(url = "${product.feign.url}", name = "product")
public interface ProductFeign {

	@GetMapping("/searchProductById/{id}")
	public ProductItem searchProductById(@RequestHeader("Authorization") String token, @PathVariable("id") int id);

}
