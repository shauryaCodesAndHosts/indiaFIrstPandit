package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.PujaCategoryDto;
import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.*;
import com.indiafirstpandit.service.CategoryService;
import com.indiafirstpandit.service.PujaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<PujaCategoryDto> getAll()
    {
        return pujaCategoryService.getAllCategories().stream().map(PujaCategoryDto::new).toList();
    }

    @PostMapping("/create")
    public PujaCategory addCategory(@RequestBody PujaCategory pujaCategory)
    {
        return pujaCategoryService.addCategory(pujaCategory);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id)
    {
        pujaCategoryService.deletePujaCategory(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<PujaCategory> updatePujaCategory(@PathVariable UUID id, @RequestBody PujaCategory updatedPujaCategory)
    {
        ServiceStatus status = pujaCategoryService.updatePujaCategory(id,updatedPujaCategory);
        if(status == ServiceStatus.Done)
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<PujaCategoryDto> getCategory(@PathVariable UUID id)
    {
        PujaCategoryDto pujaCategoryDto = pujaCategoryService.getPujaCategory(id);
        return ResponseEntity.ok(pujaCategoryDto);
    }




    @GetMapping("/getAllPujas/{id}")
    public List<PujaDto> getAllPujasInACategory(@PathVariable UUID id)
    {
        PujaCategoryDto pc = pujaCategoryService.getPujaCategory(id);
        return pc.getPujas();
    }



}
