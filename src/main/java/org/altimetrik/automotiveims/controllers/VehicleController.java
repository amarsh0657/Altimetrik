package org.altimetrik.automotiveims.controllers;

import org.altimetrik.automotiveims.entites.Part;
import org.altimetrik.automotiveims.entites.Suppliers;
import org.altimetrik.automotiveims.entites.Vehicle;
import org.altimetrik.automotiveims.services.PartService;
import org.altimetrik.automotiveims.services.SuppliersService;
import org.altimetrik.automotiveims.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SuppliersService supplierService;

    @GetMapping
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getByVehicleId(id);
    }

    @PostMapping
    public Vehicle createPart(@RequestBody Vehicle vehicle) {

        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle saveVehicle = vehicleService.getByVehicleId(id);
        saveVehicle.setMake(vehicle.getMake());
        saveVehicle.setVin(vehicle.getVin());
        saveVehicle.setPrice(vehicle.getPrice());
        saveVehicle.setModel(vehicle.getModel());
        saveVehicle.setYear(vehicle.getYear());
        saveVehicle.setQuantity(vehicle.getQuantity());

        return vehicleService.saveVehicle(saveVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }
}
