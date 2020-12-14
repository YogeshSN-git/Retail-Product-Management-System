package com.buy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	
	private int quanitity;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

}
