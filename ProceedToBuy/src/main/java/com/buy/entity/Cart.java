package com.buy.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	private int productId;
	private String zipcode;
	private int quanitity;
	private Date deliveryDate;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="vendor_id")
	private Vendor vendor;

	public Cart(int productId, String zipcode, int quanitity, Date deliveryDate, Vendor vendor) {
		super();
		this.productId = productId;
		this.zipcode = zipcode;
		this.quanitity = quanitity;
		this.deliveryDate = deliveryDate;
		this.vendor = vendor;
	}

	
}
