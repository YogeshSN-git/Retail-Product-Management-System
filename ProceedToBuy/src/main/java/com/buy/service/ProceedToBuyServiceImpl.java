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
	public Cart addToCart(String token, String customerId, int productId, String zipCode, String expectedDeliveryDate,
			int quantity) throws ParseException {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<Vendor> vendorsList = vendorFeign.getVendorDetails(productId, token);

		if (vendorsList.isEmpty()) {
			throw new OutOfStockException();
		}

		Comparator<Vendor> compare = Comparator.comparing(Vendor::getRating);
		Vendor vendorDetails = vendorsList.stream().max(compare).get();

		if (vendorRepository.existsById(vendorDetails.getVendorId())) {
			vendorDetails = vendorRepository.findById(vendorDetails.getVendorId()).get();
		}
		Cart cart = null;

		Optional<Cart> customerCart = cartRepository.findById(customerId);
		ProductItem productById = productFeign.searchProductById(token, productId);

		if (customerCart.isPresent()) {

			cart = customerCart.get();

			List<ProductItem> productList = cart.getProductList();
			isAlreadyInCart(productList, productId);

			productById.setQuanitity(quantity);
			productById.setVendor(vendorDetails);
			productList.add(productById);
			cart.setProductList(productList);

			cart.setDeliveryDate(dateFormat.parse(expectedDeliveryDate));

		} else {

			List<ProductItem> productList = new ArrayList<>();

			productById.setQuanitity(quantity);
			productById.setVendor(vendorDetails);
			productList.add(productById);

			cart = new Cart(customerId, zipCode, dateFormat.parse(expectedDeliveryDate), productList);

		}

		cartRepository.save(cart);

		return cart;
	}

	@Override
	public Boolean isAlreadyInCart(List<ProductItem> productList, int productId) {

		boolean incart = productList.stream().anyMatch(product -> product.getProductId() == productId);

		if (incart) {
			log.info("Already in Cart");
			throw new AlreadyInCartException();
		}

		return false;
	}

	@Override
	public void addToWishList(String token, String customerId, int productId) {

		if (!authFeign.getValidity(token).isValid()) {
			throw new UnauthorizedException();
		}

		Optional<Wishlist> wishlistById = wishListRepository.findById(customerId);
		ProductItem productById = productFeign.searchProductById(token, productId);

		Wishlist wishlist;
		if (!wishlistById.isPresent()) {
			List<ProductItem> productList = new ArrayList<>();

			productList.add(productById);

			wishlist = new Wishlist(customerId, new Date(), productList);

		}

		else {
			wishlist = wishlistById.get();
			List<ProductItem> productList = wishlist.getProductList();

			isAlreadyInWishList(productList, productId);

			productList.add(productById);
			wishlist.setProductList(productList);
			wishlist.setDateAddedtoWishlist(new Date());
		}

		wishListRepository.save(wishlist);
	}

	@Override
	public Boolean isAlreadyInWishList(List<ProductItem> productList, int productId) {
		boolean inList = productList.stream().anyMatch(product -> (product.getProductId() == productId));

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
