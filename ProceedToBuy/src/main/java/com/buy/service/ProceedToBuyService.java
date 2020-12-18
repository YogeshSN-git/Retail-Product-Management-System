package com.buy.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.buy.entity.Cart;
import com.buy.entity.ProductItem;
import com.buy.entity.Wishlist;
import com.buy.exceptions.AlreadyInCartException;
import com.buy.exceptions.AlreadyInWishlistException;
import com.buy.exceptions.UnauthorizedException;

public interface ProceedToBuyService {

	/**
	 * ProductItem will be added to customer cart and saved in database
	 * 
	 * @param token        JWT token to validate user
	 * @param customerId   userId of customer
	 * @param productId    Id to get ProductItem
	 * @param zipCode      Product delivery zip code
	 * @param expectedDate Product expected delivery date
	 * @param quantity     Product Quantity
	 * @return if ProductItem is added to cart then return {@code Cart}
	 * 
	 * @throws ParseException        Throws ParseException if date format doesn't
	 *                               match
	 * @throws UnauthorizedException throws UnauthorizedException when token is
	 *                               invalid
	 */
	Cart addToCart(String token, String customerId, int productId, String zipCode, String expectedDate, int quantity)
			throws ParseException, UnauthorizedException;

	/**
	 * ProductItem will be added to customer wishlist and saved in database
	 * 
	 * @param token      JWT token to validate user
	 * @param customerId userId of customer
	 * @param productId  Id to get ProductItem
	 * @throws UnauthorizedException throws UnauthorizedException when token is
	 *                               invalid
	 */
	void addToWishList(String token, String customerId, int productId) throws UnauthorizedException;

	/**
	 * Gets {@code Cart} from database with id=customerId
	 * 
	 * @param token      JWT token to validate user
	 * @param customerId userId of customer
	 * @return if valid token then return {@code Optional<Cart>}
	 * @throws UnauthorizedException throws UnauthorizedException when token is
	 *                               invalid
	 */
	Optional<Cart> customerCart(String token, String customerId) throws UnauthorizedException;

	/**
	 * Gets {@code Wishlist} from database with id=customerId
	 * 
	 * @param token      JWT token to validate user
	 * @param customerId userId of customer
	 * @return if valid token then return {@code Optional<Wishlist>}
	 * @throws UnauthorizedException throws UnauthorizedException when token is not
	 *                               valid
	 */
	Optional<Wishlist> customerWishList(String token, String customerId) throws UnauthorizedException;

	/**
	 * Checks if ProductItem exist in the productList
	 * 
	 * @param productList List of products present in Wishlist
	 * @param productId   Id to get ProductItem
	 * @return If ProductItem exist with productId in the productList then return
	 *         {@code false}
	 * 
	 * @throws AlreadyInWishlistException throws AlreadyInWishlistException when
	 *                                    {@code ProductItem} is already in
	 *                                    {@code WishList}
	 */
	Boolean isAlreadyInWishList(List<ProductItem> productList, int productId) throws AlreadyInWishlistException;

	/**
	 * Checks if ProductItem exist in the productList
	 * 
	 * @param productList List of products present in cart
	 * @param productId   Id to get ProductItem
	 * @return If ProductItem exist with productId in the productList then return
	 *         {@code false}
	 * @throws AlreadyInCartException throws AlreadyInCartException when
	 *                                {@code ProductItem} is already in {@code Cart}
	 */
	Boolean isAlreadyInCart(List<ProductItem> productList, int productId) throws AlreadyInCartException;
}
