package com.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductApplicationTest {
	
	@Test
	public void contextLoad() {
		ProductApplication.main(new String[] {});
	}

}
