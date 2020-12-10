package com.vendor.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vendor.entity.AuthResponse;
import com.vendor.entity.Vendor;
import com.vendor.exception.ProductItemNotFoundException;
import com.vendor.exception.VendorNotFoundException;

public interface VendorService {
	public ResponseEntity<Vendor> getVendorDetails(int productId,String token) throws IOException, ParseException,ProductItemNotFoundException,VendorNotFoundException;
	
	public int getProductStock(int productId,String token)throws ProductItemNotFoundException ;
}
