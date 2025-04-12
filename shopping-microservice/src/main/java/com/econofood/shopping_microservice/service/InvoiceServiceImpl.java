package com.econofood.shopping_microservice.service;

import com.econofood.shopping_microservice.client.ProductClient;
import com.econofood.shopping_microservice.entity.Invoice;
import com.econofood.shopping_microservice.model.Product;
import com.econofood.shopping_microservice.repository.InvoiceRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final ProductClient productClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ProductClient productClient) {
        this.invoiceRepository = invoiceRepository;
        this.productClient = productClient;
    }

    @Override
    @CircuitBreaker(name = "productClient", fallbackMethod = "fallbackListAllCategories")
    public List<Invoice> listAllCategories() {
        List<Invoice> invoices = invoiceRepository.findAll();

        invoices.forEach((invoice) -> {
            Product product = productClient.getProductById(invoice.getProductId()).getBody();
            invoice.setProduct(product);
        });

        return invoices;
    }

    @Override
    @CircuitBreaker(name = "productClient", fallbackMethod = "fallbackGetInvoice")
    public Invoice getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);

        if (invoice != null) {
            Product product = productClient.getProductById(invoice.getProductId()).getBody();
            invoice.setProduct(product);
        }
        return invoice;
    }

    public List<Invoice> fallbackListAllCategories(Throwable t) {
        List<Invoice> invoices = invoiceRepository.findAll();

        invoices.forEach(invoice -> {
            Product product = Product.builder()
                    .build();
            invoice.setProduct(product);
        });

        return invoices;
    }

    public Invoice fallbackGetInvoice(Long id, Throwable t) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);

        if (invoice != null) {
            Product product = Product.builder()
                    .build();
            invoice.setProduct(product);
        }

        return invoice;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
