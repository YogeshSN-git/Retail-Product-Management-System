package com.buy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy.exceptions.AlreadyInWishlistException;
import com.buy.feign.VendorFeign;
import com.buy.model.Cart;
import com.buy.model.Vendor;
import com.buy.model.Wishlist;
import com.buy.repository.CartRepository;
import com.buy.repository.VendorRepository;
import com.buy.repository.WishListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProceedToBuyServiceImpl implements ProceedToBuyService {

	@Autowired
	VendorFeign vendorFeign;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	VendorRepository vendorRepository;

	@Override
	public Cart addToCart(String token, int customer_Id, int product_Id, String zip_Code, String expected_Delivery_Date,
			int quantity) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Vendor vendorDetails = vendorFeign.getVendorDetails(product_Id, token);

		if (vendorRepository.existsById(vendorDetails.getVendorId())) {
			vendorDetails = vendorRepository.findById(vendorDetails.getVendorId()).get();
		}

		Cart cart = new Cart(product_Id, zip_Code, quantity, dateFormat.parse(expected_Delivery_Date), vendorDetails);

		cartRepository.save(cart);

		return cart;
	}

	@Override
	public void addToWishList(String token, int customer_Id, int product_Id) {
		isAlreadyInWishList(customer_Id, product_Id);

		Wishlist wishlist = new Wishlist(customer_Id, product_Id, new Date());

		wishListRepository.save(wishlist);
	}

	@Override
	public Boolean isAlreadyInWishList(int customer_Id, int product_Id) {
		if (wishListRepository.existsByCustomerIdAndProductId(customer_Id, product_Id)) {
			log.info("Already in WishList");
			throw new AlreadyInWishlistException();
		}

		return true;
	}

}
