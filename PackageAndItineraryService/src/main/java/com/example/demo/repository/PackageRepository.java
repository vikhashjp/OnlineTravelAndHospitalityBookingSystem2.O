package com.example.demo.repository;

import com.example.demo.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Package entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface PackageRepository extends JpaRepository<Package, Long> {
}
