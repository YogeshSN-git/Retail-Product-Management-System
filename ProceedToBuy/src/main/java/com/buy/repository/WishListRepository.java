package com.buy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy.model.Wishlist;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Integer>{

	Boolean existsByCustomerIdAndProductId(int customerId, int productId);

}
