package com.vendor.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="vendorstock")
public class VendorStock {
	@Id
	@Column(name="productId")
	private int productId;
	//@Column(name="vendorId")
	//private int vendorId;
	@Column(name="productStock")
	private int productStock;
	@Column(name="expectedDate")
	private Date expectedDate;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="vendorId")
	private Vendor vendor;

	@Override
	public String toString() {
		return "VendorStock [productId=" + productId + ", productStock=" + productStock + ", expectedDate="
				+ expectedDate + "]";
	}
}
