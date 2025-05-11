package com.example.demo.repository;

import com.example.demo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Invoice entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
