package com.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vendor.entity.VendorStock;

public interface VendorStockRepository extends JpaRepository<VendorStock,Integer>{
	
	
}
