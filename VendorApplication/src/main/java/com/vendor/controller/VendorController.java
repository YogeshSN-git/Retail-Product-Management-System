package com.vendor.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.client.AuthClient;
import com.vendor.entity.Vendor;
import com.vendor.exception.UnauthorizedException;
import com.vendor.service.VendorService;

@RestController
public class VendorController {
	@Autowired
	VendorService vendorService;
	@Autowired
	AuthClient authClient;

	/**
	 * Call vendorService with productId argument to get list of vendor objects
	 * 
	 * @param productId Id to get ProductItem
	 * @param token     JWT token to validate user
	 * @return list of vendor objects for given productId
	 * @throws UnauthorizedException throws UnauthorizedException when token is
	 *                               invalid
	 */
	@GetMapping("/vendor/{productId}")

	public ResponseEntity<List<Vendor>> getVendorDetails(@PathVariable int productId,
			@RequestHeader("Authorization") String token) throws IOException, ParseException {
		if (authClient.getValidity(token).isValid())
			return new ResponseEntity<List<Vendor>>(vendorService.getVendorDetails(productId), HttpStatus.OK);
		else {
			throw new UnauthorizedException("Session Expired");
		}

	}

	/**
	 * Call vendorService with productId argument to get product stock number
	 * 
	 * @param productId Id to get ProductItem
	 * @param token     JWT token to validate user
	 * @return number of product stock for the given productId
	 * @throws UnauthorizedException throws UnauthorizedException when token is
	 *                               invalid
	 */

	@GetMapping("/productStock/{productId}")
	public int getProductStock(@PathVariable int productId, @RequestHeader("Authorization") String token) {
		if (authClient.getValidity(token).isValid())
			return vendorService.getProductStock(productId);
		else {
			throw new UnauthorizedException("Session Expired");
		}

	}
}
