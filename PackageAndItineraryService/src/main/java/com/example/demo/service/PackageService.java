package com.example.demo.service;

import com.example.demo.dto.PackageDTO;
import java.util.List;

public interface PackageService {
    void createPackage(PackageDTO packageDTO);
    List<PackageDTO> getAllPackages();
}
