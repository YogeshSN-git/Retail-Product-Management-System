package com.mvc.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

	private String wishListId;

	private Date dateAddedtoWishlist;

	private List<ProductItem> productList;

}
