package com.vendor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vendor.client.AuthClient;
import com.vendor.entity.AuthResponse;
import com.vendor.entity.Vendor;
import com.vendor.exception.UnauthorizedException;
import com.vendor.service.VendorService;

@SpringBootTest
public class VendorControllerTest {

	@InjectMocks
	VendorController vendorController;
	@Mock
	VendorService vendorService;
	@Mock
	AuthClient authClient;

	@Test
	void testGetVendorDetails() throws IOException, ParseException {
		List<Vendor> vendorList = new ArrayList<Vendor>();
		ResponseEntity<List<Vendor>> response = new ResponseEntity<List<Vendor>>(vendorList, HttpStatus.OK);
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(authClient.getValidity("token")).thenReturn(auth);
		when(vendorService.getVendorDetails(1)).thenReturn(vendorList);
		assertEquals(vendorController.getVendorDetails(1, "token"), response);
	}

	@Test
	void testGetProductStock() {
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(authClient.getValidity("token")).thenReturn(auth);
		when(vendorService.getProductStock(1)).thenReturn(1);
		assertEquals(vendorController.getProductStock(1, "token"), 1);
	}

	@Test
	void testUnAuthorizedException() {
		AuthResponse auth = new AuthResponse("abc", "abc", false);
		when(authClient.getValidity("token")).thenReturn(auth);
		assertThrows(UnauthorizedException.class, () -> vendorController.getVendorDetails(1, "token"));
	}

	@Test
	void testUnAuthorizedException1() {
		AuthResponse auth = new AuthResponse("abc", "abc", false);
		when(authClient.getValidity("token")).thenReturn(auth);
		assertThrows(UnauthorizedException.class, () -> vendorController.getProductStock(1, "token"));
	}
}
