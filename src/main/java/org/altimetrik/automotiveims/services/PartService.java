package org.altimetrik.automotiveims.services;

import org.altimetrik.automotiveims.entites.Part;
import org.altimetrik.automotiveims.exception.ResourceNotFoundException;
import org.altimetrik.automotiveims.repo.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Part not found",""));
    }

    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }
}
