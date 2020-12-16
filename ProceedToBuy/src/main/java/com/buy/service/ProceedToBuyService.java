package com.buy.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.buy.entity.Cart;
import com.buy.entity.ProductItem;
import com.buy.entity.Wishlist;

public interface ProceedToBuyService {

	Cart addToCart(String token, String customerId, int productId, String zipCode, String expectedDate,
			int quantity) throws ParseException;

	void addToWishList(String token, String customerId, int productId);

	Optional<Cart> customerCart(String token, String customerId);

	Optional<Wishlist> customerWishList(String token, String customerId);

	Boolean isAlreadyInWishList(List<ProductItem> productList, int productId);

	Boolean isAlreadyInCart(List<ProductItem> productList, int productId);
}
