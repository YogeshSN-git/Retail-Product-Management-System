package com.buy.service;

import com.buy.entity.Cart;

public interface ProceedToBuyService {

	Cart addToCart(String token, int customer_Id, int product_Id, String zip_Code, String expected_Delivery_Date,
			int quantity) ;

	void addToWishList(String token, int customer_Id, int product_Id);

	Boolean isAlreadyInWishList(int customer_Id, int product_Id);
}
