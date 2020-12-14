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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	AuthClient authClient;

	@GetMapping("/productList")
	public ResponseEntity<List<ProductItem>> getAll(@RequestHeader("Authorization") String token) throws InvalidUser {
		log.info("Inside Getall Controller");
		productService.getValidity(token);
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/searchProductById/{id}")
	public ResponseEntity<ProductItem> searchProductById(@RequestHeader("Authorization") String token,
			@PathVariable("id") int id) throws InvalidUser {
		log.info("Inside ProductbyId controller");
		productService.getValidity(token);
		return new ResponseEntity<>(productService.searchProductById(id, token), HttpStatus.OK);

	}

	@GetMapping("/searchProductByName/{name}")
	public ResponseEntity<ProductItem> searchProductByName(@RequestHeader("Authorization") String token,
			@PathVariable("name") String name) throws InvalidUser {
		log.info("Inside ProductByName controller");
		productService.getValidity(token);
		return new ResponseEntity<ProductItem>(productService.searchProductByName(name, token), HttpStatus.OK);

	}

	@PostMapping("/addProductRating/{id}/{rating}")
	public ResponseEntity<Msg> addProductRating(@RequestHeader("Authorization") String token,
			@PathVariable("id") int id, @PathVariable("rating") int rating) throws InvalidUser {
		log.info("Inside AddProductRating controller");
		productService.getValidity(token);
		productService.addProductRating(token, id, rating);
		return ResponseEntity.ok().body(new Msg("Thanks for Rating !! Updated successfully", HttpStatus.ACCEPTED));
	}

}
