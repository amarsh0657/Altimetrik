package org.altimetrik.automotiveims.services;

import org.altimetrik.automotiveims.entites.Part;
import org.altimetrik.automotiveims.entites.Suppliers;
import org.altimetrik.automotiveims.exception.ResourceNotFoundException;
import org.altimetrik.automotiveims.repo.PartRepository;
import org.altimetrik.automotiveims.repo.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersService {

    @Autowired
    SupplierRepository supplierRepository;

    public  List<Suppliers> getAllSupplier(){
        return supplierRepository.findAll();
    }

    public Suppliers getBySupplierId (Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Part not found",""));
    }

    public Suppliers saveSupplier(Suppliers suppliers) {
        return supplierRepository.save(suppliers);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
