package com.buy.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.buy.entity.Cart;
import com.buy.entity.ProductItem;
import com.buy.entity.Wishlist;

public interface ProceedToBuyService {

	Cart addToCart(String token, int customer_Id, int product_Id, String zip_Code, String expected_Delivery_Date,
			int quantity) throws ParseException;

	void addToWishList(String token, int customer_Id, int product_Id);

	Optional<Cart> customerCart(String token, int customerId);

	Optional<Wishlist> customerWishList(String token, int customerId);

	Boolean isAlreadyInWishList(List<ProductItem> productList, int product_Id);

	Boolean isAlreadyInCart(List<ProductItem> productList, int product_Id);
}
