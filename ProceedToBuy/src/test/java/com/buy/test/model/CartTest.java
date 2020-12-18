package com.buy.test.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.buy.entity.Cart;

public class CartTest {

	Cart cart = new Cart();

	@Test
	public void test() {
		cart.setCartId("cartId");
		cart.setDeliveryDate(new Date());
		cart.setProductList(new ArrayList<>());
		cart.setZipcode("zipcode");

		assertNotNull(new Cart(cart.getCartId(), cart.getZipcode(), cart.getDeliveryDate(), cart.getProductList()));
	}

	@Test
	public void builderTest() {
		Cart cart = Cart.builder().cartId("cartId").deliveryDate(new Date()).productList(null).zipcode("zipcode")
				.build();
		assertTrue(cart.builder().toString().contains("cartId"));
	}

}
