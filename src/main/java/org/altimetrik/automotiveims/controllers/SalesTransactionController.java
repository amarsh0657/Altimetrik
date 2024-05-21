package org.altimetrik.automotiveims.controllers;


import org.altimetrik.automotiveims.entites.Part;
import org.altimetrik.automotiveims.entites.SalesTransaction;
import org.altimetrik.automotiveims.entites.Suppliers;
import org.altimetrik.automotiveims.entites.Vehicle;
import org.altimetrik.automotiveims.services.PartService;
import org.altimetrik.automotiveims.services.SalesTransactionService;
import org.altimetrik.automotiveims.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/salesTransaction")
public class SalesTransactionController {

    @Autowired
    SalesTransactionService salesTransactionService;

    @Autowired
    PartService partService;

    @Autowired
    VehicleService vehicleService;


    @GetMapping()
    public List<SalesTransaction> getAllSalesTransaction() {
        return salesTransactionService.getAllSalesTransaction();
    }

    @GetMapping("/{id}")
    public SalesTransaction getSalesTransactionsById(@PathVariable Long id) {
        return salesTransactionService.getBySalesTransactionId(id);
    }

    @PostMapping
    public SalesTransaction createSalesTransactions(@RequestBody SalesTransaction salesTransaction) {
        if ((salesTransaction.getPart() != null && salesTransaction.getPart().getId() != null) &&
                (salesTransaction.getVehicle() != null && salesTransaction.getVehicle().getId() != null)
        ) {
            Part part =  partService.getPartById(salesTransaction.getPart().getId());
            salesTransaction.setPart(part);
            Vehicle vehicle =  vehicleService.getByVehicleId(salesTransaction.getVehicle().getId());
            salesTransaction.setVehicle(vehicle);
        }
        return salesTransactionService.saveSalesTransaction(salesTransaction);
    }


    @PutMapping("{id}")
    public SalesTransaction updateSalesTransactions(@PathVariable Long id,@RequestBody SalesTransaction salesTransaction) {
        SalesTransaction updateSale = salesTransactionService.getBySalesTransactionId(id);

        updateSale.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        updateSale.setQuantity(salesTransaction.getQuantity());
        updateSale.setTotalPrice(salesTransaction.getTotalPrice());

        if ((salesTransaction.getPart() != null && salesTransaction.getPart().getId() != null) &&
                (salesTransaction.getVehicle() != null && salesTransaction.getVehicle().getId() != null)
        ) {
            Part part =  partService.getPartById(salesTransaction.getPart().getId());
            salesTransaction.setPart(part);
            Vehicle vehicle =  vehicleService.getByVehicleId(salesTransaction.getVehicle().getId());
            salesTransaction.setVehicle(vehicle);
        }
        return salesTransactionService.saveSalesTransaction(salesTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesTransactions(@PathVariable Long id) {
        salesTransactionService.deleteSalesTransaction(id);
        return ResponseEntity.ok().build();
    }



}
