package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.repo.SamagriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SamagriService {

    @Autowired
    private SamagriRepository samagriRepository;

    public List<Samagri> getAllSamagris() {
        return samagriRepository.findAll();
    }

    public Samagri getSamagriById(UUID id) {
        return samagriRepository.findById(id).orElseThrow(() -> new RuntimeException("Samagri not found"));
    }

    public List<Samagri> getSamagrisByName(String name) {
        return samagriRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Samagri> getSamagrisByProduct(UUID productId) {
        return samagriRepository.findByProductsId(productId);
    }

    public Samagri saveSamagri(Samagri samagri) {
        return samagriRepository.save(samagri);
    }

    public void deleteSamagri(UUID id) {
        samagriRepository.deleteById(id);
    }
}
