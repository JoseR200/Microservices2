package com.econofood.product_microservice.controller;

import com.econofood.product_microservice.entity.Category;
import com.econofood.product_microservice.entity.Product;
import com.econofood.product_microservice.service.CategoryService;
import com.econofood.product_microservice.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Product", description = "Product Management Endpoints")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.listAllProduct();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            return ResponseEntity.badRequest().body("Debe proporcionar una categoría con ID.");
        }

        Category categoryExists = categoryService.getCategory(product.getCategory().getId());
        if (categoryExists == null) {
            return ResponseEntity.badRequest().body("La categoría con ID " + product.getCategory().getId() + " no existe.");
        }

        Product createdProduct = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);

        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product deletedProduct = productService.deleteProduct(id);

        if (deletedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deletedProduct);
    }

    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryId") Long id) {
        List<Product> products = productService.findByCategory(Category.builder().id(id).build());

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @PutMapping(value = "/stock/{id}")
    public ResponseEntity<Product> updateStock(@PathVariable("id") Long id, @RequestParam(name = "quantity") double quantity) {
        Product updatedProduct = productService.updateStock(id, quantity);

        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }
}
