package com.econofood.product_microservice.service;

import com.econofood.product_microservice.entity.Category;
import com.econofood.product_microservice.entity.Product;
import com.econofood.product_microservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("Created");
        product.setCreatedAt(new Date());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());

        if (productDB == null) {
            return null;
        }

        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setPrice(product.getPrice());
        productDB.setCategory(product.getCategory());

        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);

        if (productDB == null) {
            return null;
        }

        productDB.setStatus("Deleted");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);

        if (productDB == null) {
            return null;
        }

        double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }
}
