package com.buy.service;

import java.text.ParseException;

import com.buy.model.Cart;

public interface ProceedToBuyService {

	Cart addToCart(String token, int customer_Id, int product_Id, String zip_Code, String expected_Delivery_Date,
			int quantity) throws ParseException;

	void addToWishList(String token, int customer_Id, int product_Id);

	Boolean isAlreadyInWishList(int customer_Id, int product_Id);
}
