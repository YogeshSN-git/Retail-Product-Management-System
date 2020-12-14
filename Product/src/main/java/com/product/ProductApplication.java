package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class ProductApplication {

	public static void main(String[] args) {
		log.info("ProductApplication started");
		SpringApplication.run(ProductApplication.class, args);
	}

}
