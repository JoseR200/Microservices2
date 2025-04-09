package com.econofood.shopping_microservice.controller;

import com.econofood.shopping_microservice.entity.Invoice;
import com.econofood.shopping_microservice.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invoices")
@Tag(name = "Invoice", description = "Invoice Management Endpoints")
public class InvoiceController {
    private final InvoiceService invoiceService;
    
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllCategories() {
        List<Invoice> categories = invoiceService.listAllCategories();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.getInvoice(id);

        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
    }
}
