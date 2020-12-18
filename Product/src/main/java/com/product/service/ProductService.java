package com.product.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import com.product.exception.ProductIdNotFoundException;
import com.product.exception.ProductNameNotFoundException;
import com.product.exception.RatingNotValidException;
import com.product.model.AuthResponse;
import com.product.model.ProductItem;

public interface ProductService {
	/**
	 * Validation of token is made
	 * 
	 * @param token JWT token
	 * @return After validation return {@code AuthResponse}
	 */
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);

	/**
	 * Fetches {@code ProductItem} list of all product details
	 * 
	 * @return Product List {@code List<ProductItem>}
	 */
	public List<ProductItem> getAll();

	/**
	 * Fetches {@code ProductItem} from database with id=productId
	 * 
	 * @param id    productId
	 * @param token JWT token
	 * @return Product details of given productId {@code ProductItem}
	 * @throws ProductIdNotFoundException Throws exception when productId is not
	 *                                    found
	 */

	public ProductItem searchProductById(int id, String token) throws ProductIdNotFoundException;

	/**
	 * Fetches {@code ProductItem} from database with name=productName
	 * 
	 * @param name  productName
	 * @param token JWT token
	 * @return Product details of given productName {@code ProductItem}
	 * @throws ProductNameNotFoundException Throws exception when productName is not
	 *                                      found
	 */
	public ProductItem searchProductByName(String name, String token) throws ProductNameNotFoundException;

	/**
	 * Rating is added to the product for id=productId
	 * 
	 * @param token  JWT token
	 * @param id     productId
	 * @param rating productRating
	 * @throws ProductIdNotFoundException Throws exception when productId is not
	 *                                    found
	 * @throws RatingNotValidException    Throws exception when Rating is not valid
	 */
	public void addProductRating(String token, int id, int rating)
			throws ProductIdNotFoundException, RatingNotValidException;

}
