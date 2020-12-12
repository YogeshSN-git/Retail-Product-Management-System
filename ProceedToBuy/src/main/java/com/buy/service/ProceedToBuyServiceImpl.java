package com.buy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy.entity.Cart;
import com.buy.entity.ProductItem;
import com.buy.entity.Vendor;
import com.buy.entity.Wishlist;
import com.buy.exceptions.AlreadyInCartException;
import com.buy.exceptions.AlreadyInWishlistException;
import com.buy.exceptions.OutOfStockException;
import com.buy.exceptions.UnauthorizedException;
import com.buy.feign.AuthFeign;
import com.buy.feign.ProductFeign;
import com.buy.feign.VendorFeign;
import com.buy.repository.CartRepository;
import com.buy.repository.ProductRepository;
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
	ProductFeign productFeign;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Cart addToCart(String token, String customer_Id, int product_Id, String zip_Code,
			String expected_Delivery_Date, int quantity) throws ParseException {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<Vendor> vendorsList = vendorFeign.getVendorDetails(product_Id, token);

		if (vendorsList.isEmpty()) {
			throw new OutOfStockException();
		}

		Comparator<Vendor> compare = Comparator.comparing(Vendor::getRating);
		Vendor vendorDetails = vendorsList.stream().max(compare).get();

		if (vendorRepository.existsById(vendorDetails.getVendorId())) {
			vendorDetails = vendorRepository.findById(vendorDetails.getVendorId()).get();
		}
		Cart cart = null;

		Optional<Cart> customerCart = cartRepository.findById(customer_Id);
		ProductItem productById = productFeign.searchProductById(token, product_Id);

		if (!customerCart.isEmpty()) {

			cart = customerCart.get();

			List<ProductItem> productList = cart.getProductList();
			isAlreadyInCart(productList, product_Id);

			productById.setQuanitity(quantity);
			productById.setVendor(vendorDetails);
			productList.add(productById);
			cart.setProductList(productList);

			cart.setDeliveryDate(dateFormat.parse(expected_Delivery_Date));

		} else {

			List<ProductItem> productList = new ArrayList<>();

			productById.setQuanitity(quantity);
			productById.setVendor(vendorDetails);
			productList.add(productById);

			cart = new Cart(customer_Id, zip_Code, dateFormat.parse(expected_Delivery_Date), productList);

		}

		cartRepository.save(cart);

		return cart;
	}

	@Override
	public Boolean isAlreadyInCart(List<ProductItem> productList, int product_Id) {

		boolean incart = productList.stream().anyMatch(product -> (product.getProductId() == product_Id));

		if (incart) {
			log.info("Already in Cart");
			throw new AlreadyInCartException();
		}

		return false;
	}

	@Override
	public void addToWishList(String token, String customer_Id, int product_Id) {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		Optional<Wishlist> wishlistById = wishListRepository.findById(customer_Id);
		ProductItem productById = productFeign.searchProductById(token, product_Id);

		Wishlist wishlist;
		if (wishlistById.isEmpty()) {
			List<ProductItem> productList = new ArrayList<>();

			productList.add(productById);

			wishlist = new Wishlist(customer_Id, new Date(), productList);

		}

		else {
			wishlist = wishlistById.get();
			List<ProductItem> productList = wishlist.getProductList();

			isAlreadyInWishList(productList, product_Id);

			productList.add(productById);
			wishlist.setProductList(productList);
			wishlist.setDateAddedtoWishlist(new Date());
		}

		wishListRepository.save(wishlist);
	}

	@Override
	public Boolean isAlreadyInWishList(List<ProductItem> productList, int product_Id) {
		boolean inList = productList.stream().anyMatch(product -> (product.getProductId() == product_Id));

		if (inList) {
			log.info("Already in WishList");
			throw new AlreadyInWishlistException();
		}

		return false;
	}

	@Override
	public Optional<Cart> customerCart(String token, String customerId) {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		return cartRepository.findById(customerId);
	}

	@Override
	public Optional<Wishlist> customerWishList(String token, String customerId) {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		return wishListRepository.findById(customerId);
	}

}
