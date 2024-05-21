package org.altimetrik.automotiveims.controllers;

import org.altimetrik.automotiveims.entites.Part;
import org.altimetrik.automotiveims.entites.Suppliers;
import org.altimetrik.automotiveims.services.PartService;
import org.altimetrik.automotiveims.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/v1/parts")
public class PartController {


    @Autowired
    private PartService partService;

    @Autowired
    private SuppliersService supplierService;

    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }

    @PostMapping
    public Part createPart(@RequestBody Part part) {
        if (part.getSuppliers() != null && part.getSuppliers().getId() != null) {
            Suppliers supplier =  supplierService.getBySupplierId(part.getSuppliers().getId());
            part.setSuppliers(supplier);
        }
        return partService.savePart(part);
    }

    @PutMapping("/{id}")
    public Part updatePart(@PathVariable Long id, @RequestBody Part partDetails) {
        Part part = partService.getPartById(id);

        part.setName(partDetails.getName());
        part.setDescription(partDetails.getDescription());
        part.setQuantity(partDetails.getQuantity());
        part.setPrice(partDetails.getPrice());

        if (partDetails.getSuppliers() != null && partDetails.getSuppliers().getId() != null) {
            Suppliers supplier = supplierService.getBySupplierId(partDetails.getSuppliers().getId());
            part.setSuppliers(supplier);
        } else {
            part.setSuppliers(null);
        }

        return partService.savePart(part);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.ok().build();
    }
}
