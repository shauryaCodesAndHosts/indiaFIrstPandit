package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Priest;
import com.indiafirstpandit.repo.PriestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriestService {

    @Autowired
    private PriestRepository priestRepository;

    public List<Priest> getAllPriests() {
        return priestRepository.findAll();
    }

    public Priest getPriestById(UUID id) {
        return priestRepository.findById(id).orElseThrow(() -> new RuntimeException("Priest not found"));
    }

    public List<Priest> searchPriestsByExpertise(String expertise) {
        return priestRepository.findByExpertiseContainingIgnoreCase(expertise);
    }

    public List<Priest> searchPriestsByName(String name) {
        return priestRepository.findByNameContainingIgnoreCase(name);
    }

    public Priest savePriest(Priest priest) {
        return priestRepository.save(priest);
    }

    public void deletePriest(UUID id) {
        priestRepository.deleteById(id);
    }
}
