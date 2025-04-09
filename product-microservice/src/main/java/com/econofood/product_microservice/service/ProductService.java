package com.econofood.product_microservice.service;

import com.econofood.product_microservice.entity.Category;
import com.econofood.product_microservice.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProduct();
    Product getProduct(Long id);

    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);

    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);
}
