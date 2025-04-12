package com.econofood.shopping_microservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private double stock;
    private double price;
    private String status;
    private Date createdAt;
    private Category category;
}
