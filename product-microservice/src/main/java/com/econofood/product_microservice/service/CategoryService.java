package com.econofood.product_microservice.service;

import com.econofood.product_microservice.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listAllCategories();
    Category getCategory(Long id);

    Category createCategory(Category category);
    Category updateCategory(Category category);
    Category deleteCategory(Long id);
}