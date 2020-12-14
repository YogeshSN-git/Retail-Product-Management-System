package com.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.client.AuthClient;
import com.product.exception.ProductIdNotFoundException;
import com.product.exception.ProductNameNotFoundException;
import com.product.model.AuthResponse;
import com.product.model.Msg;
import com.product.model.ProductItem;
import com.product.service.ProductService;

@SpringBootTest
public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	@Mock
	ProductService productService;
	@Mock
	AuthClient authClient;
	
	@Test
	public void testValidate()
	{
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(productService.getValidity("token")).thenReturn(auth);
		assertEquals(productService.getValidity("token"), auth);
		
	}
	
	@Test
	public void testGetAll()
	{
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(productService.getValidity("token")).thenReturn(auth);
		List<ProductItem> productList= new ArrayList<>();
		productList.add(new ProductItem(1, "bag", "bag", 1000,"bag",5,4));
		productList.add(new ProductItem(2, "book", "book",500,"book",4,4));
		ResponseEntity<List<ProductItem>> response = new ResponseEntity<List<ProductItem>>(productList,HttpStatus.OK); 
		when(productService.getAll()).thenReturn(productList);
		assertEquals(productController.getAll("token"), response);
	}
	
	@Test
	public void testSearchProductById() throws ProductIdNotFoundException
	{
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(productService.getValidity("token")).thenReturn(auth);
		ProductItem product= new ProductItem(1, "bag", "bag", 1000,"bag",5,4);
		ResponseEntity<ProductItem> response = new ResponseEntity<ProductItem>(product,HttpStatus.OK);
		when(productService.searchProductById(1,"token")).thenReturn(product);
		assertEquals(productController.searchProductById("token", 1), response);
		
		
	}
	

	@Test
	public void testSearchProductByName() throws ProductNameNotFoundException
	{
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(productService.getValidity("token")).thenReturn(auth);
		ProductItem product= new ProductItem(1, "bag", "bag", 1000,"bag",5,4);
		ResponseEntity<ProductItem> response = new ResponseEntity<ProductItem>(product,HttpStatus.OK);
		when(productService.searchProductByName("bag", "token")).thenReturn(product);
		assertEquals(productController.searchProductByName("token","bag"), response);	
	}
	
	@Test
	public void testAddProductRating()
	{
		
		AuthResponse auth = new AuthResponse("abc", "abc", true);
		when(productService.getValidity("token")).thenReturn(auth);
		ResponseEntity<Msg> response= new ResponseEntity<Msg>(HttpStatus.OK);
		assertEquals(productController.addProductRating("token",1,1).getStatusCodeValue(), response.getStatusCodeValue());		
	}
}
	