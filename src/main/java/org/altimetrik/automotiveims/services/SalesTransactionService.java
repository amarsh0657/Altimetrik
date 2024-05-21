package org.altimetrik.automotiveims.services;

import org.altimetrik.automotiveims.entites.SalesTransaction;
import org.altimetrik.automotiveims.entites.Vehicle;
import org.altimetrik.automotiveims.exception.ResourceNotFoundException;
import org.altimetrik.automotiveims.repo.SalesTransactionRepository;
import org.altimetrik.automotiveims.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesTransactionService {

    @Autowired
    SalesTransactionRepository salesTransactionRepository;




    public List<SalesTransaction> getAllSalesTransaction(){
        return salesTransactionRepository.findAll();
    }

    public SalesTransaction getBySalesTransactionId (Long id) {
        return salesTransactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Part not found",""));
    }

    public SalesTransaction saveSalesTransaction(SalesTransaction salesTransaction) {
        return salesTransactionRepository.save(salesTransaction);
    }

    public void deleteSalesTransaction(Long id) {
        salesTransactionRepository.deleteById(id);
    }
}
