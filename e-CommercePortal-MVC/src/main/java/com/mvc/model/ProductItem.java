package com.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public @Data class ProductItem {

	private int productId;
	private String productName;
	private String description;
	private int productPrice;
	private String imageName;
	private double rating;

}
