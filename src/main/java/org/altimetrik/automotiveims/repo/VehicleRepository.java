package org.altimetrik.automotiveims.repo;

import org.altimetrik.automotiveims.entites.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
