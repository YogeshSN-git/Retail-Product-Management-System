package com.buy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy.entity.Cart;
import com.buy.entity.Vendor;
import com.buy.entity.Wishlist;
import com.buy.exceptions.AlreadyInWishlistException;
import com.buy.exceptions.OutOfStockException;
import com.buy.exceptions.UnauthorizedException;
import com.buy.feign.AuthFeign;
import com.buy.feign.VendorFeign;
import com.buy.repository.CartRepository;
import com.buy.repository.VendorRepository;
import com.buy.repository.WishListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProceedToBuyServiceImpl implements ProceedToBuyService {

	@Autowired
	AuthFeign authFeign;

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
			int quantity) {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<Vendor> vendorsList = vendorFeign.getVendorDetails(product_Id, token);

		if(vendorsList.isEmpty()) {
			throw new OutOfStockException();
		}

		Comparator<Vendor> compare = Comparator.comparing(Vendor::getRating);
		Vendor vendorDetails = vendorsList.stream().max(compare).get();

		if (vendorRepository.existsById(vendorDetails.getVendorId())) {
			vendorDetails = vendorRepository.findById(vendorDetails.getVendorId()).get();
		}

		Cart cart = null;
		try {
			cart = new Cart(customer_Id,product_Id, zip_Code, quantity, dateFormat.parse(expected_Delivery_Date), vendorDetails);
		} catch (ParseException e) {
			throw new DateTimeException("Invalid date format");
		}

		cartRepository.save(cart);

		return cart;
	}

	@Override
	public void addToWishList(String token, int customer_Id, int product_Id) {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

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
