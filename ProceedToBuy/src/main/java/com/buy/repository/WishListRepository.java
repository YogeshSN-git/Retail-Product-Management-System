package com.buy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy.entity.Wishlist;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Integer> {


}
