package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.PujaCategoryDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.PujaCategory;
import com.indiafirstpandit.repo.PujaCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PujaCategoryService {


    @Autowired
    private PujaCategoryRepository pujaCategoryRepository;


    public List<PujaCategory> getAllCategories(){
        return pujaCategoryRepository.findAll();
    }

    public PujaCategory addCategory(PujaCategory pujaCategory)
    {
        pujaCategoryRepository.save(pujaCategory);
        return pujaCategory;
    }


    public void deletePujaCategory(UUID id) {
        pujaCategoryRepository.deleteById(id);
    }




    public PujaCategoryDto getPujaCategory(UUID id) {
        PujaCategory pujaCategory= pujaCategoryRepository.findById(id).orElse(new PujaCategory());
        return new PujaCategoryDto(pujaCategory);
    }






    public ServiceStatus updatePujaCategory(UUID id, PujaCategory updatedPujaCategory) {
        PujaCategory existingPujaCategory = pujaCategoryRepository.findById(id).orElse(new PujaCategory());
        if(existingPujaCategory.getId()==null)
        {
            return ServiceStatus.Not_Found;
        }
        else {
            existingPujaCategory.setName(updatedPujaCategory.getName());
            existingPujaCategory.setImage(updatedPujaCategory.getImage());
            existingPujaCategory.setDescription(updatedPujaCategory.getDescription());
            existingPujaCategory.setTotalItems(updatedPujaCategory.getTotalItems());
            pujaCategoryRepository.save(existingPujaCategory);
            return ServiceStatus.Done;
        }
    }
}