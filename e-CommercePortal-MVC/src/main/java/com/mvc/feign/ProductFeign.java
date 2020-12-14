package com.mvc.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.model.ProductItem;
import com.mvc.requestAndResponse.Msg;

@FeignClient(url = "${product.feign.url}", name = "productfeign")
public interface ProductFeign {
	@GetMapping("/productList")
	public List<ProductItem> getAll(@RequestHeader("Authorization") String token);

	@GetMapping("/searchProductById/{id}")
	public ProductItem searchProductById(@RequestHeader("Authorization") String token, @PathVariable("id") int id);

	@RequestMapping(path = "/searchProductByName/{name}", method = RequestMethod.GET)
	public ProductItem searchProductByName(@RequestHeader(name = "Authorization") String token,
			@PathVariable("name") String name);

	@PostMapping("/addProductRating/{id}/{rating}")
	public Msg addProductRating(@RequestHeader("Authorization") String token, @PathVariable("id") int id,
			@PathVariable("rating") int rating) ;

}
