package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.PujaCategory;
import com.indiafirstpandit.repo.PujaCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PujaCategory getCategory(UUID id) {
        return pujaCategoryRepository.getReferenceById(id);
    }
}