package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.product.client.AuthClient;
import com.product.exception.InvalidUser;
import com.product.model.Msg;
import com.product.model.ProductItem;
import com.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	AuthClient authClient;

	/**
	 * Retrieves List comprising Product details
	 * 
	 * @param token JWT token for validation
	 * @return List of Product details
	 * @throws InvalidUser Throws exception if token is invalid
	 */
	@GetMapping("/productList")
	public ResponseEntity<List<ProductItem>> getAll(@RequestHeader("Authorization") String token) throws InvalidUser {
		productService.getValidity(token);
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	/**
	 * Retrieves product details for given productId
	 * 
	 * @param token JWT token for validation
	 * @param id    productId
	 * @return ProductItem object for given input productId
	 * @throws InvalidUser Throws exception if token is invalid
	 */
	@GetMapping("/searchProductById/{id}")
	public ResponseEntity<ProductItem> searchProductById(@RequestHeader("Authorization") String token,
			@PathVariable("id") int id) throws InvalidUser {
		productService.getValidity(token);
		return new ResponseEntity<>(productService.searchProductById(id, token), HttpStatus.OK);

	}

	/**
	 * Retrieves product details for given productName
	 * 
	 * @param token JWT token for validation
	 * @param name  productName
	 * @return ProductItem object for given input productName
	 * @throws InvalidUser Throws exception if token is invalid
	 */
	@GetMapping("/searchProductByName/{name}")
	public ResponseEntity<ProductItem> searchProductByName(@RequestHeader("Authorization") String token,
			@PathVariable("name") String name) throws InvalidUser {
		productService.getValidity(token);
		return new ResponseEntity<ProductItem>(productService.searchProductByName(name, token), HttpStatus.OK);

	}

	/**
	 * Rating is added to the given productId
	 * 
	 * @param token  JWT token for validation
	 * @param id     productId
	 * @param rating productRating
	 * @return {@code ResponseEntity<MessageResponse>}
	 * @throws InvalidUser Throws exception if token is invalid
	 */
	@PostMapping("/addProductRating/{id}/{rating}")
	public ResponseEntity<Msg> addProductRating(@RequestHeader("Authorization") String token,
			@PathVariable("id") int id, @PathVariable("rating") int rating) throws InvalidUser {
		productService.getValidity(token);
		productService.addProductRating(token, id, rating);
		return ResponseEntity.ok().body(new Msg("Thanks for Rating !! Updated successfully", HttpStatus.ACCEPTED));
	}

}
