package com.econofood.shopping_microservice.service;

import com.econofood.shopping_microservice.entity.Invoice;
import com.econofood.shopping_microservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> listAllCategories() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
