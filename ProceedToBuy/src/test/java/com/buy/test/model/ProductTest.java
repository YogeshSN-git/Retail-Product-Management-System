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

		assertTrue(product.toString().contains("imageName"));
		assertNotNull(new ProductItem(product.getProductId(), product.getProductName(), product.getDescription(),
				product.getProductPrice(), product.getImageName(), product.getRating(), product.getQuanitity(),
				vendor));
	}
}
