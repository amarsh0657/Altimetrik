package org.altimetrik.automotiveims.repo;

import org.altimetrik.automotiveims.entites.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
