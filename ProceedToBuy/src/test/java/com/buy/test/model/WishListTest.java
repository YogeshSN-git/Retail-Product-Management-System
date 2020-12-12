package com.buy.test.model;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.buy.entity.Wishlist;

public class WishListTest {
	Wishlist wishlist = new Wishlist();

	@Test
	public void test() {
		wishlist.setDateAddedtoWishlist(new Date());
		wishlist.setProductList(new ArrayList<>());
		wishlist.setWishListId("wishListId");

		assertNotNull(
				new Wishlist(wishlist.getWishListId(), wishlist.getDateAddedtoWishlist(), wishlist.getProductList()));
	}
}
