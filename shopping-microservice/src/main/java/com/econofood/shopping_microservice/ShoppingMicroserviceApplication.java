package com.econofood.shopping_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.econofood.shopping_microservice.client")
@SpringBootApplication
public class ShoppingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingMicroserviceApplication.class, args);
	}

}
