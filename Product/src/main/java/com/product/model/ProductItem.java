package com.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductItem {

	@Id
	@Column(name = "product_id")
	private int productId;
	@Column(name = "product_name", length = 20)
	private String productName;
	@Column(name = "description", length = 20)
	private String description;
	@Column(name = "product_price")
	private int productPrice;
	@Column(name = "image_name")
	private String imageName;
	@Column(name = "rating")
	private double rating;

	@JsonIgnore
	private int ratedCount;

	@Override
	public String toString() {
		return "ProductItem [productId=" + productId + ",price=" + productPrice + ",productName=" + productName
				+ ",Description=" + description + ", imageName=" + imageName + "]";
	}
}
