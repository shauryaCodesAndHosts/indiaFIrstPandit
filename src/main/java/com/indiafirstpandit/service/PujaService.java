package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.repo.PujaCategoryRepository;
import com.indiafirstpandit.repo.PujaRepository;
import com.indiafirstpandit.repo.SamagriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PujaService {

    @Autowired
    private PujaRepository pujaRepository;

    @Autowired
    private PujaCategoryRepository pujaCategoryRepository;

    @Autowired
    private SamagriRepository samagriRepository;

    public List<Puja> getAllPujas() {
        return pujaRepository.findAll();
    }

    public Puja getPujaById(UUID id) {
        return pujaRepository.findById(id).orElseThrow(() -> new RuntimeException("Puja not found"));
    }

    public List<Puja> getPujasByName(String name) {
        return pujaRepository.findByNameContainingIgnoreCase(name);
    }
//
//    public List<Puja> getPujasByPanditCount(int panditCount) {
//        return pujaRepository.findByPanditNeeded(panditCount);
//    }

//    public List<Puja> getPujasByAmountLessThan(Double amount) {
//        return pujaRepository.findByAmountLessThanEqual(amount);
//    }

    public Puja savePuja(PujaDto pujaDto) {
        Puja puja = new Puja();
        //add this to puja entity as a constructor so that it can return puja object by receiving the DTO object
        puja.setName(pujaDto.getName());
        puja.setDescription(pujaDto.getDescription());
        puja.setBenefits(pujaDto.getBenefits());
        puja.setMantra(pujaDto.getMantra());
        puja.setPanditNeeded(pujaDto.getPanditNeeded());
        puja.setFreqExtraAddedPandit(pujaDto.getFreqExtraAddedPandit());
        puja.setAmount(pujaDto.getAmount());
        puja.setImage1(pujaDto.getImage1());
        puja.setImage2(pujaDto.getImage2());
        puja.setImage3(pujaDto.getImage3());
        puja.setPujaCategory(pujaCategoryRepository.getReferenceById(pujaDto.getPujaCategoryId()));
//        if (pujaDto.getSamagriId()!=null)
//        {
//            puja.setSamagri(samagriRepository.findById(pujaDto.getSamagriId()).orElse(null));
//        }
        return pujaRepository.save(puja);
    }

    public void deletePuja(UUID id) {
        pujaRepository.deleteById(id);
    }

    public ServiceStatus updatePuja(UUID id,PujaDto updatedPujaDto){
        if (pujaRepository.existsById(id)) {
            Puja existingPuja = pujaRepository.findById(id).orElse(new Puja());
            existingPuja.setName(updatedPujaDto.getName());
            existingPuja.setDescription(updatedPujaDto.getDescription());
            existingPuja.setBenefits(updatedPujaDto.getBenefits());
            existingPuja.setMantra(updatedPujaDto.getMantra());
            existingPuja.setPanditNeeded(updatedPujaDto.getPanditNeeded());
            existingPuja.setFreqExtraAddedPandit(updatedPujaDto.getFreqExtraAddedPandit());
            existingPuja.setAmount(updatedPujaDto.getAmount());
            existingPuja.setImage1(updatedPujaDto.getImage1());
            existingPuja.setImage2(updatedPujaDto.getImage2());
            existingPuja.setImage3(updatedPujaDto.getImage3());
            if (updatedPujaDto.getPujaCategoryId()!=null) {
                existingPuja.setPujaCategory(pujaCategoryRepository.getReferenceById(updatedPujaDto.getPujaCategoryId()));
            }
//            if (updatedPujaDto.getSamagriId()!=null) {
//                existingPuja.setSamagri(samagriRepository.getReferenceById(updatedPujaDto.getSamagriId()));
//            }
            pujaRepository.save(existingPuja);
            return ServiceStatus.Done;
        }
        else {
            return ServiceStatus.Not_Found;
        }
    }
}
