package com.example.demo.service;

import com.example.demo.dto.PackageDTO;
import com.example.demo.model.Package;
import com.example.demo.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public void createPackage(PackageDTO packageDTO) {
        Package packageEntity = new Package();
        packageEntity.setName(packageDTO.getName());
        packageEntity.setIncludedHotels(packageDTO.getIncludedHotels());
        packageEntity.setIncludedFlights(packageDTO.getIncludedFlights());
        packageEntity.setActivities(packageDTO.getActivities());
        packageEntity.setPrice(packageDTO.getPrice());
        packageRepository.save(packageEntity);
    }

    @Override
    public List<PackageDTO> getAllPackages() {
        List<Package> packages = packageRepository.findAll();
        List<PackageDTO> packageDTOs = new ArrayList<>();

        for (Package packageEntity : packages) {
            PackageDTO dto = new PackageDTO();
            dto.setId(packageEntity.getId());
            dto.setName(packageEntity.getName());
            dto.setIncludedHotels(packageEntity.getIncludedHotels());
            dto.setIncludedFlights(packageEntity.getIncludedFlights());
            dto.setActivities(packageEntity.getActivities());
            dto.setPrice(packageEntity.getPrice());
            packageDTOs.add(dto);
        }
        return packageDTOs;
    }
}
