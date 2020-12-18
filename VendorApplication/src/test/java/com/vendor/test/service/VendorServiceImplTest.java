package com.vendor.test.service;

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

import com.vendor.entity.Vendor;
import com.vendor.entity.VendorStock;
import com.vendor.exception.OutOfStockException;
import com.vendor.exception.ProductItemNotFoundException;
import com.vendor.repository.VendorRepository;
import com.vendor.repository.VendorStockRepository;
import com.vendor.service.VendorServiceImpl;

@SpringBootTest
public class VendorServiceImplTest {
	@InjectMocks
	VendorServiceImpl vendorServiceImpl;
	@Mock
	VendorRepository vendorRepository;
	@Mock
	VendorStockRepository vendorStockRepository;

	@Test
	void testGetVendorDetails() throws ProductItemNotFoundException, IOException, ParseException {
		VendorStock stock = new VendorStock();
		stock.setProductId(1);
		Vendor vendor = new Vendor();
		List<Vendor> vendorList = new ArrayList<Vendor>();
		List<VendorStock> stockList = new ArrayList<VendorStock>();
		stockList.add(stock);
		vendorList.add(vendor);
		stock.setVendor(vendor);
		when(vendorStockRepository.existsByProductId(1)).thenReturn(true);
		when(vendorStockRepository.findByProductIdAndProductStockGreaterThan(1,0)).thenReturn(stockList);
		assertEquals(vendorServiceImpl.getVendorDetails(1), vendorList);
	}

	@Test
	void testGetProductStock() {
		VendorStock stock = new VendorStock();
		when(vendorStockRepository.findFirstByProductIdOrderByProductStockDesc(1)).thenReturn(stock);
		assertEquals(vendorServiceImpl.getProductStock(1), 0);
	}

	@Test
	void testProductItemNotFoundException() {
		VendorStock stock = null;
		when(vendorStockRepository.findFirstByProductIdOrderByProductStockDesc(1)).thenReturn(stock);
		assertThrows(ProductItemNotFoundException.class, () -> vendorServiceImpl.getProductStock(1));
	}

	@Test
	void testProductNotFoundException() {
		List<VendorStock> stockList = new ArrayList<VendorStock>();
		when(vendorStockRepository.findByProductIdAndProductStockGreaterThan(1,0)).thenReturn(stockList);
		assertThrows(ProductItemNotFoundException.class, () -> vendorServiceImpl.getVendorDetails(1));
	}

	@Test
	void testOutOfStockException()
	{
		VendorStock stock = new VendorStock();
		Vendor vendor = new Vendor();
		List<Vendor> vendorList = new ArrayList<Vendor>();
		List<VendorStock> stockList = new ArrayList<VendorStock>();
		stockList.add(stock);
		vendorList.add(vendor);
		stock.setVendor(vendor);
		when(vendorStockRepository.existsByProductId(1)).thenReturn(true);
		when(vendorStockRepository.findByProductIdAndProductStockGreaterThan(2,0)).thenReturn(stockList);
		assertThrows(OutOfStockException.class,()->vendorServiceImpl.getVendorDetails(1));
	}
}
