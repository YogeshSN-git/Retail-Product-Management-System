package com.vendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendor.entity.VendorStock;

public interface VendorStockRepository extends JpaRepository<VendorStock,Integer>{
	
	List<VendorStock> findByProductId(int productId);
	VendorStock findFirstByProductIdOrderByProductStockDesc(int productId);
}
