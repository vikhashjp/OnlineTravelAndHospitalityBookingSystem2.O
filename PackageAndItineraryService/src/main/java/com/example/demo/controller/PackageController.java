package com.example.demo.controller;

import com.example.demo.dto.PackageDTO;
import com.example.demo.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin(origins = "*") // Enables CORS for frontend interaction
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping
    public ResponseEntity<String> createPackage(@Valid @RequestBody PackageDTO packageDTO) {
        try {
            packageService.createPackage(packageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Package created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create package: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllPackages() {
        try {
            List<PackageDTO> packages = packageService.getAllPackages();
            if (packages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No packages found");
            }
            return ResponseEntity.ok(packages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching packages: " + e.getMessage());
        }
    }
}
