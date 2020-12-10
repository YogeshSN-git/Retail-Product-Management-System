package com.vendor.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.client.AuthClient;
import com.vendor.entity.Vendor;
import com.vendor.exception.UnauthorizedException;
import com.vendor.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VendorController {
	@Autowired
	VendorService vendorService;
	@Autowired
	AuthClient authClient;
	
	@GetMapping("/vendor/{productId}")
	public ResponseEntity<List<Vendor>> getVendorDetails(@PathVariable int productId,@RequestHeader("Authorization") String token) throws IOException, ParseException
	{	
		log.info("Controller to getVendorDetails");
		if(authClient.getValidity(token).isValid())
			return vendorService.getVendorDetails(productId,token);
		else
		{
			log.info("Unauthorized user");
			throw new UnauthorizedException("Session Expired");
		}
			
	}
	@GetMapping("/productStock/{productId}")
	public int getProductStock(@PathVariable int productId,@RequestHeader("Authorization") String token)
	{
		log.info("Controller to getProductStockDetail");
		if(authClient.getValidity(token).isValid())
			return vendorService.getProductStock(productId, token);
		else
		{
			log.info("Unauthorized user");
			throw new UnauthorizedException("Session Expired");
		}
		
	}
}
