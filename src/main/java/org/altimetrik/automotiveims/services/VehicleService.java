package org.altimetrik.automotiveims.services;

import org.altimetrik.automotiveims.entites.Suppliers;
import org.altimetrik.automotiveims.entites.Vehicle;
import org.altimetrik.automotiveims.exception.ResourceNotFoundException;
import org.altimetrik.automotiveims.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;


    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }

    public Vehicle getByVehicleId (Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Part not found",""));
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

}
