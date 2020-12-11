package com.vendor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vendor.entity.Vendor;
import com.vendor.entity.VendorStock;
import com.vendor.exception.ProductItemNotFoundException;
import com.vendor.exception.VendorNotFoundException;
import com.vendor.repository.VendorRepository;
import com.vendor.repository.VendorStockRepository;

@SpringBootTest
public class VendorServiceImplTest {
	@InjectMocks
	VendorServiceImpl vendorServiceImpl;
	@Mock
	VendorRepository vendorRepository;
	@Mock
	VendorStockRepository vendorStockRepository;
	
	@Test
	void testGetVendorDetails() throws ProductItemNotFoundException, VendorNotFoundException, IOException, ParseException
	{	
		VendorStock stock= new VendorStock();
		Vendor vendor= new Vendor();
		Optional<Vendor> data = Optional.of(vendor);
		List<Vendor> vendorList= new ArrayList<Vendor>();
		List<VendorStock> stockList= new ArrayList<VendorStock>();
		stockList.add(stock);
		when(vendorStockRepository.findByProductId(1)).thenReturn(stockList);
		when(vendorRepository.findById(1)).thenReturn(data);
		ResponseEntity<List<Vendor>> response = new ResponseEntity<List<Vendor>>(vendorList,HttpStatus.OK);
		assertEquals(vendorServiceImpl.getVendorDetails(1,"token"), response);
	}
	@Test
	void testGetProductStock()
	{
		VendorStock stock= new VendorStock();
		when(vendorStockRepository.findFirstByProductIdOrderByProductStockDesc(1)).thenReturn(stock);
		assertEquals(vendorServiceImpl.getProductStock(1, "token"),0);
	}
	@Test
	void testProductItemNotFoundException()
	{
		VendorStock stock= null;
		when(vendorStockRepository.findFirstByProductIdOrderByProductStockDesc(1)).thenReturn(stock);
		assertThrows(ProductItemNotFoundException.class,()->vendorServiceImpl.getProductStock(1,"token"));
	}
	@Test
	void testProductNotFoundException()
	{
		List<VendorStock> stockList= new ArrayList<VendorStock>();
		when(vendorStockRepository.findByProductId(1)).thenReturn(stockList);
		assertThrows(ProductItemNotFoundException.class,()->vendorServiceImpl.getVendorDetails(1,"token"));
	}
	@Test
	void test() throws ProductItemNotFoundException, VendorNotFoundException, IOException, ParseException
	{
		Vendor vendor = new Vendor();
		vendor.setVendorId(1);
		Optional<Vendor> data = Optional.of(vendor);
		VendorStock stock= new VendorStock();
		stock.setVendor(vendor);
		stock.setProductStock(1);
		List<VendorStock> stockList= new ArrayList<VendorStock>();
		List<Vendor> vendorList= new ArrayList<Vendor>();
		stockList.add(stock);
		when(vendorStockRepository.findByProductId(1)).thenReturn(stockList);
		when(vendorRepository.findById(1)).thenReturn(data);
		ResponseEntity<List<Vendor>> response = new ResponseEntity<List<Vendor>>(vendorList,HttpStatus.OK);
		ResponseEntity<List<Vendor>> result = vendorServiceImpl.getVendorDetails(1,"token");
		assertEquals(result.getStatusCodeValue(), response.getStatusCodeValue());
		
	}
}
