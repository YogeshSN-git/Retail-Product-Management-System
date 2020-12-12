package com.buy.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.buy.response.AuthResponse;
import com.buy.service.ProceedToBuyServiceImpl;

@SpringBootTest
public class ServiceTest {

	List<ProductItem> productList = new ArrayList<>();
	ProductItem item1 = new ProductItem(1, "productName", "description", 10, "imageName", 3, 3, new Vendor());
	ProductItem item2 = new ProductItem(2, "productName", "description", 10, "imageName", 3, 3, new Vendor());
	ProductItem item3 = new ProductItem(3, "productName", "description", 10, "imageName", 3, 3, new Vendor());
	ProductItem item4 = new ProductItem(4, "productName", "description", 10, "imageName", 3, 3, new Vendor());

	@InjectMocks
	ProceedToBuyServiceImpl serviceImpl;

	@Mock
	AuthFeign authFeign;

	@Mock
	VendorFeign vendorFeign;

	@Mock
	ProductFeign productFeign;

	@Mock
	CartRepository cartRepository;

	@Mock
	WishListRepository wishListRepository;

	@Mock
	VendorRepository vendorRepository;

	@Mock
	ProductRepository productRepository;

	@Test
	public void isNotAlreadyInCartTest() {
		productList.add(item1);
		productList.add(item2);
		productList.add(item3);
		productList.add(item4);

		assertFalse(serviceImpl.isAlreadyInCart(productList, 5));
	}

	@Test
	public void isAlreadyInCartTest() {
		productList.add(item1);

		assertThrows(AlreadyInCartException.class, () -> serviceImpl.isAlreadyInCart(productList, 1));
	}

	@Test
	public void isNotAlreadyInWishListTest() {
		productList.add(item1);
		productList.add(item2);
		productList.add(item3);
		productList.add(item4);

		assertFalse(serviceImpl.isAlreadyInWishList(productList, 5));
	}

	@Test
	public void isAlreadyInWishListTest() {
		productList.add(item1);

		assertThrows(AlreadyInWishlistException.class, () -> serviceImpl.isAlreadyInWishList(productList, 1));
	}

	@Test
	public void unauthorized1() {
		AuthResponse response = new AuthResponse("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);

		assertThrows(UnauthorizedException.class, () -> serviceImpl.customerCart("token", "admin"));
	}

	@Test
	public void unauthorized2() {
		AuthResponse response = new AuthResponse("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);

		assertThrows(UnauthorizedException.class, () -> serviceImpl.customerWishList("token", "admin"));
	}

	@Test
	public void unauthorized3() {
		AuthResponse response = new AuthResponse("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);

		assertThrows(UnauthorizedException.class,
				() -> serviceImpl.addToCart("token", "admin", 1, "zip_Code", "date", 2));
	}

	@Test
	public void unauthorized4() {
		AuthResponse response = new AuthResponse("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);

		assertThrows(UnauthorizedException.class, () -> serviceImpl.addToWishList("token", "admin", 3));
	}

	@Test
	public void customerCartTest() {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		productList.add(item1);
		productList.add(item2);
		productList.add(item3);

		Cart cart = new Cart("cartId", "zipcode", new Date(), productList);
		Optional<Cart> answer = Optional.of(cart);
		when(cartRepository.findById("admin")).thenReturn(answer);

		assertEquals("cartId", serviceImpl.customerCart("token", "admin").get().getCartId());
	}

	@Test
	public void customerWishlistTest() {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		productList.add(item1);
		productList.add(item2);
		productList.add(item3);

		Wishlist wishlist = new Wishlist("wishListId", new Date(), productList);
		Optional<Wishlist> answer = Optional.of(wishlist);
		when(wishListRepository.findById("admin")).thenReturn(answer);

		assertEquals("wishListId", serviceImpl.customerWishList("token", "admin").get().getWishListId());
	}

	@Test
	public void outOfStockTest() {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		List<Vendor> vendorList = new ArrayList<>();
		when(vendorFeign.getVendorDetails(1, "token")).thenReturn(vendorList);

		assertThrows(OutOfStockException.class,
				() -> serviceImpl.addToCart("token", "admin", 1, "zip_Code", "date", 2));

	}

	@Test
	public void newCartTest() throws ParseException {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(new Vendor(1, "vendorName", 10.2, 4));
		when(vendorFeign.getVendorDetails(1, "token")).thenReturn(vendorList);

		when(vendorRepository.existsById(1)).thenReturn(false);

		Optional<Cart> cart = Optional.empty();
		when(cartRepository.findById("admin")).thenReturn(cart);

		when(productFeign.searchProductById("token", 1)).thenReturn(item1);
		serviceImpl.addToCart("token", "admin", 1, "zip_Code", "30-12-2020", 1);
	}

	@Test
	public void addToExistingCartTest() throws ParseException {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(new Vendor(1, "vendorName", 10.2, 4));
		when(vendorFeign.getVendorDetails(1, "token")).thenReturn(vendorList);

		when(vendorRepository.existsById(1)).thenReturn(false);

		Cart cart1 = new Cart("admin", "zipcode", new Date(), productList);
		Optional<Cart> cart = Optional.of(cart1);
		when(cartRepository.findById("admin")).thenReturn(cart);

		when(productFeign.searchProductById("token", 1)).thenReturn(item1);
		serviceImpl.addToCart("token", "admin", 1, "zip_Code", "30-12-2020", 1);
	}

	@Test
	public void newWishListTest() {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		Optional<Wishlist> wishList = Optional.empty();
		when(wishListRepository.findById("admin")).thenReturn(wishList);

		when(productFeign.searchProductById("token", 1)).thenReturn(item1);
		serviceImpl.addToWishList("token", "admin", 1);

	}

	@Test
	public void addToExistingWishListTest() {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		Wishlist wishList1 = new Wishlist("admin", new Date(), productList);
		Optional<Wishlist> wishList = Optional.of(wishList1);
		when(wishListRepository.findById("admin")).thenReturn(wishList);

		when(productFeign.searchProductById("token", 1)).thenReturn(item1);
		serviceImpl.addToWishList("token", "admin", 1);
	}

	@Test
	public void vendorExistTest() throws ParseException {
		AuthResponse response = new AuthResponse("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);

		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(new Vendor(1, "vendorName", 10.2, 4));
		when(vendorFeign.getVendorDetails(1, "token")).thenReturn(vendorList);

		when(vendorRepository.existsById(1)).thenReturn(true);

		when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
		Optional<Cart> cart = Optional.empty();
		when(cartRepository.findById("admin")).thenReturn(cart);

		when(productFeign.searchProductById("token", 1)).thenReturn(item1);
		serviceImpl.addToCart("token", "admin", 1, "zip_Code", "30-12-2020", 1);
	}


}
