package com.vendor.test.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.vendor.entity.Vendor;
import com.vendor.entity.VendorStock;

public class VendorStockTest {
	VendorStock vendorStock = new VendorStock();
	Vendor vendor= new Vendor();
	
	@Test
	void testVendorStockId()
	{
		vendorStock.setVendorStockId(1);
		assertEquals(vendorStock.getVendorStockId(), 1);
	}
	@Test
	void testProductId()
	{
		vendorStock.setProductId(1);
		assertEquals(vendorStock.getProductId(),1);
	}
	@Test
	void testProductStock()
	{
		vendorStock.setProductStock(1);
		assertEquals(vendorStock.getProductStock(),1);
	}
	@Test
	void testExceptedDate()
	{
		Date date = new Date();
		vendorStock.setExpectedDate(date);
		assertEquals(vendorStock.getExpectedDate(), date);
	}
	@Test
	void testVendor()
	{
		vendorStock.setVendor(vendor);
		assertEquals(vendorStock.getVendor(),vendor);
	}
}
