package com.buy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buy.entity.ProductItem;

public interface ProductRepository extends JpaRepository<ProductItem, Integer> {

}
