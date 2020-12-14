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
public class Cart {

	private String cartId;

	private String zipcode;
	private Date deliveryDate;

	private List<ProductItem> productList;

}
