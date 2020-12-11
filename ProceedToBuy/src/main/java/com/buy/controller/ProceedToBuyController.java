package com.buy.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.buy.entity.Cart;
import com.buy.entity.Wishlist;
import com.buy.response.MessageResponse;
import com.buy.service.ProceedToBuyService;

@RestController
public class ProceedToBuyController {

	@Autowired
	ProceedToBuyService proceedToBuyService;

	@PostMapping("/addProductToCart/{customerid}/{productid}/{zipcode}/{deliverydate}/{quantity}")
	public Cart addProductToCart(@RequestHeader("Authorization") final String token, @PathVariable int customerid,
			@PathVariable int productid, @PathVariable String zipcode, @PathVariable String deliverydate,
			@PathVariable int quantity) throws ParseException {

		return proceedToBuyService.addToCart(token, customerid, productid, zipcode, deliverydate, quantity);
	}

	@PostMapping("/addProductToWishlist/{customerid}/{productid}")
	public ResponseEntity<MessageResponse> addProductToWishlist(@RequestHeader("Authorization") final String token,
			@PathVariable int customerid, @PathVariable int productid) {

		proceedToBuyService.addToWishList(token, customerid, productid);
		return ResponseEntity.ok().body(new MessageResponse("Added to WishList", HttpStatus.ACCEPTED));
	}

	@PostMapping("/viewCart/{customerid}")
	public Optional<Cart> viewCart(@RequestHeader("Authorization") final String token, @PathVariable int customerid) {

		return proceedToBuyService.customerCart(token, customerid);
	}

	@PostMapping("/viewWishList/{customerid}")
	public Optional<Wishlist> viewWishList(@RequestHeader("Authorization") final String token, @PathVariable int customerid) {

		return proceedToBuyService.customerWishList(token, customerid);
	}
}
