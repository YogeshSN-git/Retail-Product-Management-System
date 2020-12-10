package com.vendor.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vendor.entity.Vendor;
import com.vendor.entity.VendorStock;
import com.vendor.exception.ProductItemNotFoundException;
import com.vendor.exception.VendorNotFoundException;
import com.vendor.repository.VendorRepository;
import com.vendor.repository.VendorStockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorRepository vendorRepository;
	@Autowired 
	VendorStockRepository vendorStockRepository;
	@Override
	public ResponseEntity<Vendor> getVendorDetails(int productId,String token) throws IOException, ParseException,ProductItemNotFoundException,VendorNotFoundException {
		Vendor vendor=null;
		VendorStock stock=null;
		Optional<VendorStock> stock1=vendorStockRepository.findById(productId);
		stock1.orElseThrow(()->new ProductItemNotFoundException("Product Id not found"));
		stock = stock1.get();
		if(stock.getProductStock()>0)
		{
			vendor=stock.getVendor();
			Optional<Vendor> vendor1 = vendorRepository.findById(vendor.getVendorId());
			vendor1.orElseThrow(()->new VendorNotFoundException("Vendor not found"));
			vendor= vendor1.get();
			return new ResponseEntity<Vendor>(vendor,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Vendor>(vendor,HttpStatus.BAD_REQUEST);
	}
	@Override
	public int getProductStock(int productId, String token)throws ProductItemNotFoundException {
		
		Optional<VendorStock> stock= vendorStockRepository.findById(productId);
		stock.orElseThrow(()->new ProductItemNotFoundException("Product Id not found"));
		return stock.get().getProductStock();
	}



	


}
