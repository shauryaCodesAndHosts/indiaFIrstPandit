package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.*;
import com.indiafirstpandit.service.CategoryService;
import com.indiafirstpandit.service.PujaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pujaCategory")
public class PujaCategoryController {

    @Autowired
    private PujaCategoryService pujaCategoryService;

    @GetMapping("/getAll")
    public List<PujaCategory> getAll()
    {
        return pujaCategoryService.getAllCategories();
    }

    @PostMapping("/addCategory")
    public PujaCategory addCategory(@RequestBody PujaCategory pujaCategory)
    {
        return pujaCategoryService.addCategory(pujaCategory);
    }

    @GetMapping("/getAllPujas/{id}")
    public List<Puja> getAllPujasInACategory(@PathVariable UUID id)
    {
        PujaCategory pc = pujaCategoryService.getCategory(id);
        return pc.getPujas();
    }

}
