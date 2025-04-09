package com.econofood.product_microservice.controller;


import com.econofood.product_microservice.entity.Category;
import com.econofood.product_microservice.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "Category", description = "Category Management Endpoints")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.listAllCategories();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.getCategory(id);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        category.setId(id);
        Category updatedCategory = categoryService.updateCategory(category);

        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) {
        Category deletedCategory = categoryService.deleteCategory(id);

        if (deletedCategory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deletedCategory);
    }
}
