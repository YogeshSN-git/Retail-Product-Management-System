package com.product.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.model.ProductItem;

public interface ProductRepository extends JpaRepository<ProductItem,Integer>{

	Optional<ProductItem> findByProductNameContainingIgnoreCase(String name);


	 
}
