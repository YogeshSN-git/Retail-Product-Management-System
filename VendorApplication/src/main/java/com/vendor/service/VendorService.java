package com.vendor.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.vendor.entity.Vendor;
import com.vendor.exception.ProductItemNotFoundException;

public interface VendorService {

	/**
	 * Retrieves vendor details from database which contains product stock more than
	 * 0 for given productId
	 * 
	 * @param productId Id to get ProductItem
	 * @return list of vendor objects for given productId
	 * @throws ProductItemNotFoundException throws when productId is not found
	 */
	public List<Vendor> getVendorDetails(int productId)
			throws IOException, ParseException, ProductItemNotFoundException;

	/**
	 * Return highest product stock of given productId among all vendor stocks
	 * 
	 * @param productId Id to get ProductItem
	 * @return the number of products available for given productId
	 * @throws ProductItemNotFoundException throws when productId is not found
	 */
	public int getProductStock(int productId) throws ProductItemNotFoundException;
}
