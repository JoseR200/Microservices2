package com.econofood.shopping_microservice.entity;

import com.econofood.shopping_microservice.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Product product;
}
