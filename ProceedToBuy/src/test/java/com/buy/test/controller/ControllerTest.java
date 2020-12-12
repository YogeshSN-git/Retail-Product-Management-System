package com.buy.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.buy.controller.ProceedToBuyController;
import com.buy.entity.Cart;
import com.buy.entity.Wishlist;
import com.buy.service.ProceedToBuyService;

@SpringBootTest
public class ControllerTest {

	@InjectMocks
	ProceedToBuyController controller;

	@Mock
	ProceedToBuyService service;

	@Test
	public void addToCart() throws ParseException {

		when(service.addToCart("token", "admin", 1, "zip_Code", "date", 2)).thenReturn(new Cart());
		controller.addProductToCart("token", "admin", 1, "zip_Code", "date", 2);

	}

	@Test
	public void addToWishList() {
		assertEquals(200, controller.addProductToWishlist("token", "admin", 1).getStatusCodeValue());
	}

	@Test
	public void showCart() {

		Optional<Cart> cart = Optional.of(new Cart());
		when(service.customerCart("token", "customerid")).thenReturn(cart);
		controller.viewCart("token", "customerid");
	}

	@Test
	public void showWishList() {

		Optional<Wishlist> wishList= Optional.of(new Wishlist());
		when(service.customerWishList("token", "customerid")).thenReturn(wishList);
		controller.viewWishList("token", "customerid");
	}
}
