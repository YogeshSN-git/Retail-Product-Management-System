package com.product.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import com.product.exception.ProductIdNotFoundException;
import com.product.exception.ProductNameNotFoundException;
import com.product.exception.RatingNotValidException;
import com.product.model.AuthResponse;
import com.product.model.ProductItem;

public interface ProductService {
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);

	public List<ProductItem> getAll();

	public ProductItem searchProductById(int id, String token) throws ProductIdNotFoundException;

	public ProductItem searchProductByName(String name, String token) throws ProductNameNotFoundException;

	public void addProductRating(String token, int id, int rating)
			throws ProductIdNotFoundException, RatingNotValidException;

}
