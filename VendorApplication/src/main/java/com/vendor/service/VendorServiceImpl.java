package com.vendor.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor.entity.Vendor;
import com.vendor.entity.VendorStock;
import com.vendor.exception.OutOfStockException;
import com.vendor.exception.ProductItemNotFoundException;
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
	public List<Vendor> getVendorDetails(int productId)
			throws IOException, ParseException, ProductItemNotFoundException {
		log.info("Entered service to get vendor details ");

		if (!vendorStockRepository.existsByProductId(productId)) {
			log.info("Product Id not found");
			throw new ProductItemNotFoundException("Product Id not found");
		}

		List<VendorStock> stockList = vendorStockRepository.findByProductIdAndProductStockGreaterThan(productId, 0);

		if (stockList.size() == 0) {
			log.info("Product Out of stock");
			throw new OutOfStockException();
		}

		List<Vendor> vendorList = stockList.stream().map(VendorStock::getVendor).collect(Collectors.toList());

		return vendorList;
	}

	@Override
	public int getProductStock(int productId) throws ProductItemNotFoundException {
		VendorStock stock = vendorStockRepository.findFirstByProductIdOrderByProductStockDesc(productId);
		if (stock == null) {
			throw new ProductItemNotFoundException("Product Id not found");
		} else
			return stock.getProductStock();
	}

}
