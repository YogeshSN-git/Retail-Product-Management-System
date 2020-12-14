package com.product.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductItemTest {
	ProductItem productItem = new ProductItem();
	ProductItem product = new ProductItem();
	ProductItem productItem1 = new ProductItem(1, "abc", "abc", 1, "abc", 1, 5);

	@Test
	void testProductId() {
		productItem.setProductId(1);
		assertEquals(productItem.getProductId(), 1);
	}

	@Test
	void testProductName() {
		productItem.setProductName("Book");
		assertEquals(productItem.getProductName(), "Book");
	}

	@Test
	void testDescription() {
		productItem.setDescription("Fastrack");
		assertEquals(productItem.getDescription(), "Fastrack");
	}

	@Test
	void testProductPrice() {
		productItem.setProductPrice(499);
		assertEquals(productItem.getProductPrice(), 499);
	}

	@Test
	void testImageName() {
		productItem.setImageName("Book");
		assertEquals(productItem.getImageName(), "Book");
	}

	@Test
	void testRating() {
		productItem.setRating(4);
		assertEquals(productItem.getRating(), 4);
	}

	@Test
	void testToString() {
		String str = productItem1.toString();
		assertEquals(productItem1.toString(), str);
	}

	@Test
	public void test() {
		product.setRatedCount(3);

		assertTrue(product.toString().contains("imageName"));
		assertNotNull(new ProductItem(product.getProductId(), product.getProductName(), product.getDescription(),
				product.getProductPrice(), product.getImageName(), product.getRating(), 0));
	}

}
