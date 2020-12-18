package com.vendor.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class VendorTest 
{
	Vendor vendor= new Vendor();
	List<VendorStock> list = new ArrayList<VendorStock>();
	
	@Test
	void testVendorId()
	{
		vendor.setVendorId(1);
		assertEquals(vendor.getVendorId(),1);
	}
	@Test
	void testVendorName()
	{
		vendor.setVendorName("abc");
		assertEquals(vendor.getVendorName(),"abc");
	}
	@Test
	void testDeliveryCharge()
	{
		vendor.setDeliveryCharge(1.00);
		assertEquals(vendor.getDeliveryCharge(),1.00);
	}
	@Test
	void testRating()
	{
		vendor.setRating(1);
		assertEquals(vendor.getRating(),1);
	}
	@Test
	void testVendorStockList()
	{
		vendor.setVendorStockList(list);
		assertEquals(vendor.getVendorStockList(),list);
	}
}
