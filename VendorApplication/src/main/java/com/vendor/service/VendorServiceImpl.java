package com.vendor.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
	public ResponseEntity<List<Vendor>> getVendorDetails(int productId,String token) throws IOException, ParseException,ProductItemNotFoundException,VendorNotFoundException 
	{
		log.info("Entered service to get vendor details ");
		List<Vendor> vendorList= new ArrayList<Vendor>();
		List<VendorStock> stockList=vendorStockRepository.findByProductId(productId);
		if(stockList.size()==0)
		{
			log.info("Product Id not found");
			throw new ProductItemNotFoundException("Product Id not found");
		}
		for(VendorStock s:stockList)
		{	
			Vendor vendor=null;
			if(s.getProductStock()>0)
			{
				vendor=s.getVendor();
				Optional<Vendor> vendor1 = vendorRepository.findById(vendor.getVendorId());
				vendorList.add(vendor1.get());
			}
		}
			return new ResponseEntity<List<Vendor>>(vendorList,HttpStatus.OK);
	}
	@Override
	public int getProductStock(int productId, String token)throws ProductItemNotFoundException
	{
		log.info("Entered service to get product stock detail ");
		VendorStock stock= vendorStockRepository.findFirstByProductIdOrderByProductStockDesc(productId);
		if(stock==null)
		{
			log.info("Product Id not found");
			throw new ProductItemNotFoundException("Product Id not found");
		}
		else
			return stock.getProductStock();
	}



	


}
