package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.repo.PujaCategoryRepository;
import com.indiafirstpandit.repo.PujaRepository;
import com.indiafirstpandit.repo.SamagriRepository;
import com.indiafirstpandit.requests.PujaCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Puja savePuja(PujaCreateRequest pujaCreateRequest) {
        Puja puja = new Puja();
        //add this to puja entity as a constructor so that it can return puja object by receiving the DTO object
        puja.setName(pujaCreateRequest.getName());
        puja.setDescription(pujaCreateRequest.getDescription());
        puja.setBenefits(pujaCreateRequest.getBenefits());
        puja.setMantra(pujaCreateRequest.getMantra());
        puja.setPanditNeeded(pujaCreateRequest.getPanditNeeded());
        puja.setFreqExtraAddedPandit(pujaCreateRequest.getFreqExtraAddedPandit());
        puja.setPricePerExtraPandit(pujaCreateRequest.getPricePerExtraPandit());
        puja.setMaxPandits(pujaCreateRequest.getMaxPandits());
        puja.setAmount(pujaCreateRequest.getAmount());
        puja.setImage1(pujaCreateRequest.getImage1());
        puja.setImage2(pujaCreateRequest.getImage2());
        puja.setImage3(pujaCreateRequest.getImage3());
        puja.setPujaCategory(pujaCategoryRepository.getReferenceById(pujaCreateRequest.getPujaCategoryId()));

        System.out.println(pujaCreateRequest.getSamagriUUIDs());
        if (pujaCreateRequest.getSamagriUUIDs()!=null)
        {
            List<Samagri> samagris = samagriRepository.findAllById(pujaCreateRequest.getSamagriUUIDs());
            puja.setSamagri(samagris);
            pujaRepository.save(puja);
            for(Samagri samagri : samagris)
            {
                if (samagri.getPujas() == null) {
                    samagri.setPujas(new ArrayList<>());
                }
                samagri.getPujas().add(puja); // Add Puja to the Samagri
            }
            samagriRepository.saveAll(samagris);
        }
        return pujaRepository.save(puja);
    }

    public void deletePuja(UUID id) {
        pujaRepository.deleteById(id);
    }

    public ServiceStatus updatePuja(UUID id,PujaCreateRequest updatedPujaDto){
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
            if (updatedPujaDto.getSamagriUUIDs()!=null)
            {
                List<Samagri> samagris = samagriRepository.findAllById(updatedPujaDto.getSamagriUUIDs());
                existingPuja.setSamagri(samagris);
                pujaRepository.save(existingPuja);
                for(Samagri samagri : samagris)
                {
                    if (samagri.getPujas() == null) {
                        samagri.setPujas(new ArrayList<>());
                    }
                    samagri.getPujas().add(existingPuja); // Add Puja to the Samagri
                }
                samagriRepository.saveAll(samagris);
            }

            pujaRepository.save(existingPuja);
            return ServiceStatus.Done;
        }
        else {
            return ServiceStatus.Not_Found;
        }
    }
}
