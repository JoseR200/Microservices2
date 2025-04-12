package com.econofood.shopping_microservice.client;

import com.econofood.shopping_microservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-microservice")
public interface ProductClient {
    @GetMapping(value = "/products/{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id") Long id);
}
