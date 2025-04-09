package com.econofood.shopping_microservice.repository;

import com.econofood.shopping_microservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
