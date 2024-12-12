package com.indiafirstpandit.service;

import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.repo.PujaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PujaService {

    @Autowired
    private PujaRepository pujaRepository;

    public List<Puja> getAllPujas() {
        return pujaRepository.findAll();
    }

    public Puja getPujaById(UUID id) {
        return pujaRepository.findById(id).orElseThrow(() -> new RuntimeException("Puja not found"));
    }

    public List<Puja> getPujasByName(String name) {
        return pujaRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Puja> getPujasByPanditCount(int panditCount) {
        return pujaRepository.findByPanditNeeded(panditCount);
    }

    public List<Puja> getPujasByAmountLessThan(Double amount) {
        return pujaRepository.findByAmountLessThanEqual(amount);
    }

    public Puja savePuja(Puja puja) {
        return pujaRepository.save(puja);
    }

    public void deletePuja(UUID id) {
        pujaRepository.deleteById(id);
    }

    public ServiceStatus updatePuja(UUID id,Puja updatedPuja){
        Puja exisitngPuja = pujaRepository.findById(id).get();
        if (exisitngPuja.getId()==null)
        {
            return ServiceStatus.Not_Found;
        }
        else
        {
            updatedPuja.setId(exisitngPuja.getId());
            pujaRepository.save(updatedPuja);
            return ServiceStatus.Done;
        }
    }
}
