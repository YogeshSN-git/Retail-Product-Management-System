package com.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.client.AuthClient;
import com.product.exception.InvalidUser;
import com.product.exception.ProductIdNotFoundException;
import com.product.exception.ProductNameNotFoundException;
import com.product.exception.RatingNotValidException;
import com.product.model.AuthResponse;
import com.product.model.ProductItem;
import com.product.repository.ProductRepository;

@SpringBootTest
public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;
	@Mock
	ProductRepository productRepository;
	@Mock
	AuthClient authClient;

	@Test
	public void testGetAll() {
		List<ProductItem> list = new ArrayList<ProductItem>();
		when(productRepository.findAll()).thenReturn(list);
		assertEquals(productServiceImpl.getAll(), list);
	}

	@Test
	public void testGetValidity() {
		AuthResponse auth = new AuthResponse("admin", "admin", true);
		when(authClient.getValidity("token")).thenReturn(auth);
		assertEquals(productServiceImpl.getValidity("token"), auth);
	}

	@Test
	public void testInvalidUserException() {
		AuthResponse auth = new AuthResponse("admin", "admin", false);
		when(authClient.getValidity("token")).thenReturn(auth);
		assertThrows(InvalidUser.class, () -> productServiceImpl.getValidity("token"));
	}
	@Test
	public void testSearchProductById()
	{
		ProductItem product= new ProductItem(1,"abc","abc",1,"abc",1,5);
		Optional<ProductItem> data = Optional.of(product);
		when(productRepository.findById(1)).thenReturn(data);
		assertEquals(productServiceImpl.searchProductById(1,"token"),product);
	}
	@Test
	public void testProductIdNotFoundException()
	{
		ProductItem product= new ProductItem(1,"abc","abc",1,"abc",1,5);
		Optional<ProductItem> data = Optional.of(product);
		when(productRepository.findById(1)).thenReturn(data);
		assertThrows(ProductIdNotFoundException.class,()->productServiceImpl.searchProductById(2,"token"));
	}
	@Test
	public void testSearchProductByName()
	{
		ProductItem product= new ProductItem(1,"abc","abc",1,"abc",1,5);
		Optional<ProductItem> data = Optional.of(product);
		when(productRepository.findByProductNameContainingIgnoreCase("name")).thenReturn(data);
		assertEquals(productServiceImpl.searchProductByName("name","token"),product);
	}
	@Test
	public void testProductNameNotFoundException()
	{
		ProductItem product= new ProductItem(1,"abc","abc",1,"abc",1,5);
		Optional<ProductItem> data = Optional.of(product);
		when(productRepository.findByProductNameContainingIgnoreCase("name")).thenReturn(data);
		assertThrows(ProductNameNotFoundException.class,()->productServiceImpl.searchProductByName("abc","token"));
	}
	@Test
	public void testRatingNotValidException()
	{
		assertThrows(RatingNotValidException.class,()->productServiceImpl.addProductRating("token",1,-1));
	}
	@Test
	public void testRatingNotValidException1()
	{
		assertThrows(RatingNotValidException.class,()->productServiceImpl.addProductRating("token",1,6));
	}
	@Test
	public void testProductIdNotFoundException1()
	{
		assertThrows(ProductIdNotFoundException.class,()->productServiceImpl.addProductRating("token",-1,1));
	}
	@Test
	public void testAddProductRating()
	{
		when(productRepository.existsById(1)).thenReturn(true);
		ProductItem product = new ProductItem();
		Optional<ProductItem> data = Optional.of(product);
		when(productRepository.findById(1)).thenReturn(data);
		productServiceImpl.addProductRating("token",1,1);
	}
}
