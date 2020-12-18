package com.vendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendor.entity.VendorStock;

public interface VendorStockRepository extends JpaRepository<VendorStock, Integer> {

	/**
	 * Retrieves list of {@code VendorStock} objects from database which are in
	 * stock for given productId
	 * 
	 * @param productId Id to get ProductItem
	 * @return List of vendor stock objects for given productId
	 */
	List<VendorStock> findByProductIdAndProductStockGreaterThan(int productId, int productStock);

	/**
	 * Retrieves Vendor stock object from database for given productId
	 * 
	 * @param productId Id to get ProductItem
	 * @return VendorStock object for given productId
	 */
	VendorStock findFirstByProductIdOrderByProductStockDesc(int productId);

	/**
	 * @param productId Id to get ProductItem
	 * @return {@code true} if valid product
	 */
	boolean existsByProductId(int productId);
}
