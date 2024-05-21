package org.altimetrik.automotiveims.controllers;

import org.altimetrik.automotiveims.entites.Part;
import org.altimetrik.automotiveims.entites.Suppliers;
import org.altimetrik.automotiveims.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/supplier")
public class SupplierController {
    @Autowired
    SuppliersService suppliersService;

    @GetMapping()
    public List<Suppliers> getAllSuppliers() {
        return suppliersService.getAllSupplier();
    }

    @GetMapping("/{id}")
    public Suppliers getSuppliersById(@PathVariable Long id) {
        return suppliersService.getBySupplierId(id);
    }

    @PostMapping()
    public Suppliers createSuppliers(@RequestBody Suppliers suppliers) {

        return suppliersService.saveSupplier(suppliers);
    }

    @PutMapping("/{id}")
    public Suppliers updateSuppliers(@PathVariable Long id, @RequestBody Suppliers suppliers) {
        Suppliers suppliers1 = suppliersService.getBySupplierId(id);
        suppliers1.setContactInfo(suppliers.getContactInfo());
        suppliers1.setName(suppliers.getName());
        return suppliersService.saveSupplier(suppliers1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        suppliersService.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }
}
