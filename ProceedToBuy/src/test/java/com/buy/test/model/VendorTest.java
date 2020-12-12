package com.buy.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.buy.entity.Vendor;

public class VendorTest {
	Vendor vendor = new Vendor();
	Vendor vendor1 = new Vendor(1, "vendorName", 10.0, 2);

	@Test
	void testVendorId() {
		vendor.setVendorId(1);
		assertEquals(vendor.getVendorId(), 1);
	}

	@Test
	void testVendorName() {
		vendor.setVendorName("abc");
		assertEquals(vendor.getVendorName(), "abc");
	}

	@Test
	void testDeliveryCharge() {
		vendor.setDeliveryCharge(1.00);
		assertEquals(vendor.getDeliveryCharge(), 1.00);
	}

	@Test
	void testRating() {
		vendor.setRating(1);
		assertEquals(vendor.getRating(), 1);
	}
}
