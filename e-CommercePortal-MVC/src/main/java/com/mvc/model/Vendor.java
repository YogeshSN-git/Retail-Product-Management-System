package com.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Vendor {

	private int vendorId;
	private String vendorName;
	private double deliveryCharge;
	
	private int rating;
	
}
