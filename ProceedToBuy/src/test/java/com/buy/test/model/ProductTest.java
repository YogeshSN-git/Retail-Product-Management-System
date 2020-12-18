package com.buy.test.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.buy.entity.ProductItem;
import com.buy.entity.Vendor;

public class ProductTest {

	ProductItem product = new ProductItem();

	@Test
	public void test() {
		product.setDescription("description");
		product.setImageName("imageName");
		product.setProductId(1);
		product.setProductName("productName");
		product.setProductPrice(10);
		product.setQuanitity(1);
		product.setRating(3);
		Vendor vendor = new Vendor();
		product.setVendor(vendor);

		product.hashCode();

		ProductItem productItem = new ProductItem(product.getProductId(), product.getProductName(),
				product.getDescription(), product.getProductPrice(), product.getImageName(), product.getRating(),
				product.getQuanitity(), vendor);
		assertNotNull(productItem);
		assertTrue(product.toString().equals(productItem.toString()));
	}

	@Test
	public void builderTest() {
		ProductItem productItem = ProductItem.builder().productId(1).productName("productName").productPrice(10)
				.imageName("imageName").quanitity(2).description("description").rating(3).vendor(new Vendor()).build();
		
		ProductItem.builder().toString();
		assertTrue(productItem.toString().contains("productName"));
	}

}
