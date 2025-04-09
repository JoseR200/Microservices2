package com.econofood.product_microservice.repository;

import com.econofood.product_microservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
