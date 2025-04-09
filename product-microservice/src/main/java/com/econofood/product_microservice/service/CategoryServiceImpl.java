package com.econofood.product_microservice.service;

import com.econofood.product_microservice.entity.Category;
import com.econofood.product_microservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        category.setStatus("Created");

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryDB = getCategory(category.getId());

        if (categoryDB == null) {
            return null;
        }

        categoryDB.setName(category.getName());

        return categoryRepository.save(categoryDB);
    }

    @Override
    public Category deleteCategory(Long id) {
        Category categoryDB = getCategory(id);

        if (categoryDB == null) {
            return null;
        }

        categoryDB.setStatus("Deleted");
        return categoryRepository.save(categoryDB);
    }
}
