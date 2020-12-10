package com.vendor.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="vendor")
public class Vendor {
	@Id
	@Column(name="vendorId")
	private int vendorId;
	@Column(name="vendorName")
	private String vendorName;
	@Column(name="deliveryCharge")
	private double deliveryCharge;
	@Column(name="rating")
	private int rating;
	
	@JsonIgnore
	@OneToMany (cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="vendorId")
	private List<VendorStock> vendorStockList=new ArrayList<VendorStock>();
	
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", deliveryCharge=" + deliveryCharge
				+ ", rating=" + rating + "]";
	}
	
	
}
