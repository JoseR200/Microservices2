package com.econofood.product_microservice.repository;

import com.econofood.product_microservice.entity.Category;
import com.econofood.product_microservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
