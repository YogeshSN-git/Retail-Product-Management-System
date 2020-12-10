package com.buy.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.buy.exceptions.UnauthorizedException;
import com.buy.feign.AuthFeign;
import com.buy.model.Cart;
import com.buy.service.ProceedToBuyService;

@RestController
public class ProceedToBuyController {

	@Autowired
	AuthFeign authFeign;

	@Autowired
	ProceedToBuyService proceedToBuyService;

	@PostMapping("/addProductToCart/{customerid}/{productid}/{zipcode}/{deliverydate}/{quantity}")
	public Cart addProductToCart(@RequestHeader("Authorization") final String token,
			@PathVariable("customerid") int customerid, @PathVariable("productid") int productid,
			@PathVariable("zipcode") String zipcode, @PathVariable("deliverydate") String deliverydate,
			@PathVariable("quantity") int quantity) throws ParseException {

		if (authFeign.getValidity(token).isValid()) {
			return proceedToBuyService.addToCart(token, customerid, productid, zipcode, deliverydate, quantity);
		}

		throw new UnauthorizedException("Unauthorized");
	}

	@PostMapping("/addProductToWishlist/{customerid}/{productid}")
	public ResponseEntity<?> addProductToWishlist(@RequestHeader("Authorization") final String token,
			@PathVariable("customerid") int customerid, @PathVariable("productid") int productid) {

		if (authFeign.getValidity(token).isValid()) {
			proceedToBuyService.addToWishList(token, customerid, productid);
			return ResponseEntity.ok().body("Added to WishList");
		}

		throw new UnauthorizedException("Unauthorized");
	}

}
