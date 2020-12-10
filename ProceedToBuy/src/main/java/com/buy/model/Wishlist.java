package com.buy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int wishListId;

	private int customerId;
	private int productId;
	private Date dateAddedtoWishlist;

	public Wishlist(int customerId, int productId, Date dateAddedtoWishlist) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.dateAddedtoWishlist = dateAddedtoWishlist;
	}

}
