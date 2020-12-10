package com.vendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class VendorApplication {

	public static void main(String[] args) {
		log.info("Vendor Application Started");
		SpringApplication.run(VendorApplication.class, args);

	}

}
