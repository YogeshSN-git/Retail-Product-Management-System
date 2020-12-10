package com.vendor.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vendor.entity.Vendor;
import com.vendor.exception.ProductItemNotFoundException;
import com.vendor.exception.VendorNotFoundException;

public interface VendorService {
	public ResponseEntity<List<Vendor>> getVendorDetails(int productId,String token) throws IOException, ParseException,ProductItemNotFoundException,VendorNotFoundException;
	
	public int getProductStock(int productId,String token)throws ProductItemNotFoundException ;
}
