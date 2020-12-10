package com.vendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vendor.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {
	
	
	 

}
