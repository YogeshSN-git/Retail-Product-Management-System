package com.buy.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	/**
	 * Add ProductItem to Cart
	 * 
	 * @param token        JWT token to validate user
	 * @param customerid   userId of customer
	 * @param productid    Id to get ProductItem
	 * @param zipcode      Product delivery zipcode
	 * @param deliverydate Product expected delivery date
	 * @param quantity     Product Quantity
	 * @return if ProductItem is added to cart then return {@code Cart}
	 * @throws ParseException Throws ParseException if date format doesn't match
	 */
	@PostMapping("/addproducttocart/{customerid}/{productid}/{zipcode}/{deliverydate}/{quantity}")
	public Cart addProductToCart(@RequestHeader("Authorization") final String token, @PathVariable String customerid,
			@PathVariable int productid, @PathVariable String zipcode, @PathVariable String deliverydate,
			@PathVariable int quantity) throws ParseException {

		return proceedToBuyService.addToCart(token, customerid, productid, zipcode, deliverydate, quantity);
	}

	/**
	 * Add ProductItem to WishList
	 * 
	 * @param token      JWT token to validate user
	 * @param customerid userId of customer
	 * @param productid  Id to get ProductItem
	 * @return {@code ResponseEntity<MessageResponse>}
	 */
	@PostMapping("/addproducttowishlist/{customerid}/{productid}")
	public ResponseEntity<MessageResponse> addProductToWishlist(@RequestHeader("Authorization") final String token,
			@PathVariable String customerid, @PathVariable int productid) {

		proceedToBuyService.addToWishList(token, customerid, productid);
		return ResponseEntity.ok().body(new MessageResponse("Added to WishList", HttpStatus.ACCEPTED));
	}

	/**
	 * View cart details
	 * 
	 * @param token      JWT token to validate user
	 * @param customerid userId of customer
	 * @return if token is valid then return {@code Optional<Cart>}
	 */
	@GetMapping("/viewcart/{customerid}")
	public Optional<Cart> viewCart(@RequestHeader("Authorization") final String token,
			@PathVariable String customerid) {

		return proceedToBuyService.customerCart(token, customerid);
	}

	/**
	 * View wishList details
	 * 
	 * @param token      JWT token to validate user
	 * @param customerid userId of customer
	 * @return if token is valid then return {@code Optional<Wishlist>}
	 */
	@GetMapping("/viewwishlist/{customerid}")
	public Optional<Wishlist> viewWishList(@RequestHeader("Authorization") final String token,
			@PathVariable String customerid) {

		return proceedToBuyService.customerWishList(token, customerid);
	}
}
