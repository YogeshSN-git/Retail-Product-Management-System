package com.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ECommercePortalMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommercePortalMvcApplication.class, args);
	}

}
