package com.vendor.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.client.AuthClient;
import com.vendor.entity.Vendor;
import com.vendor.exception.UnauthorizedException;
import com.vendor.service.VendorServiceImpl;

@RestController
public class VendorController {
	@Autowired
	VendorServiceImpl vendorServiceImpl;
	@Autowired
	AuthClient authClient;
	
	@GetMapping("/vendor/{productId}")
	public ResponseEntity<Vendor> getVendorDetails(@PathVariable int productId,@RequestHeader("Authorization") String token) throws IOException, ParseException
	{
		if(authClient.getValidity(token).isValid())
			return vendorServiceImpl.getVendorDetails(productId,token);
		else
			throw new UnauthorizedException("Session Expired");
	}
	@GetMapping("/productStock/{productId}")
	public int getProductStock(@PathVariable int productId,@RequestHeader("Authorization") String token)
	{
		if(authClient.getValidity(token).isValid())
			return vendorServiceImpl.getProductStock(productId, token);
		else
			throw new UnauthorizedException("Session Expired");
		
	}
}
