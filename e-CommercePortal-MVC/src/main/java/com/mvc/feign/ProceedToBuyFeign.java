package com.mvc.feign;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mvc.model.Cart;
import com.mvc.model.Wishlist;
import com.mvc.requestAndResponse.MessageResponse;

@FeignClient(url = "${proceedtobuy.feign.url}", name = "proceedtobuy")
public interface ProceedToBuyFeign {

	@PostMapping("/addproducttocart/{customerid}/{productid}/{zipcode}/{deliverydate}/{quantity}")
	public Cart addProductToCart(@RequestHeader("Authorization") final String token, @PathVariable String customerid,
			@PathVariable int productid, @PathVariable String zipcode, @PathVariable String deliverydate,
			@PathVariable int quantity) throws ParseException;

	@PostMapping("/addproducttowishlist/{customerid}/{productid}")
	public ResponseEntity<MessageResponse> addProductToWishlist(@RequestHeader("Authorization") final String token,
			@PathVariable String customerid, @PathVariable int productid);

	@GetMapping("/viewcart/{customerid}")
	public Optional<Cart> viewCart(@RequestHeader("Authorization") final String token, @PathVariable String customerid);

	@GetMapping("/viewwishlist/{customerid}")
	public Optional<Wishlist> viewWishList(@RequestHeader("Authorization") final String token,
			@PathVariable String customerid);

}
