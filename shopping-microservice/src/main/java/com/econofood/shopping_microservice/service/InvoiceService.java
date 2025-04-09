package com.econofood.shopping_microservice.service;

import com.econofood.shopping_microservice.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> listAllCategories();
    Invoice getInvoice(Long id);

    Invoice createInvoice(Invoice invoice);
}
