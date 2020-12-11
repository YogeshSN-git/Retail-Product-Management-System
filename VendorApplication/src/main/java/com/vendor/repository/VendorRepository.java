package com.vendor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vendor.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

}
