package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.samagri.SamagriAddId;
import com.indiafirstpandit.dto.samagri.SamagriDto;
import com.indiafirstpandit.dto.samagri.SamagriInformation;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.service.SamagriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/samagris")
public class SamagriController {

    @Autowired
    private SamagriService samagriService;

    @GetMapping("/getAll")
    public List<SamagriDto> getAllSamagris() {
        return samagriService.getAllSamagris().stream().map(SamagriDto::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SamagriDto> getSamagriById(@PathVariable UUID id) {
        Samagri samagri = samagriService.getSamagriById(id);
        return ResponseEntity.ok(new SamagriDto(samagri));
    }

    @PostMapping("/create")
    public ResponseEntity<SamagriDto> createSamagri(@RequestBody Samagri samagri) {
        Samagri savedSamagri = samagriService.saveSamagri(samagri);
        return ResponseEntity.ok(new SamagriDto(savedSamagri));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ServiceStatus> editSamagri(@PathVariable UUID id , @RequestBody SamagriInformation samagriInformation)
    {

        ServiceStatus serviceStatus = samagriService.editSamagriInformation(id, samagriInformation);
        return  ResponseEntity.ok(serviceStatus);
    }

    @PutMapping("/edit/addPuja/{id}")
    public ResponseEntity<ServiceStatus> editAddSamagri(@PathVariable UUID id, @RequestBody SamagriAddId samagriAddId)
    {
        ServiceStatus serviceStatus = samagriService.addPuja(id, samagriAddId);
        return ResponseEntity.ok(serviceStatus);
    }


    @PutMapping("/edit/addProduct/{id}")
    public ResponseEntity<ServiceStatus> editAddProduct(@PathVariable UUID id, @RequestBody SamagriAddId samagriAddId )
    {
        ServiceStatus serviceStatus = samagriService.addProduct(id, samagriAddId);
        return ResponseEntity.ok(serviceStatus);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSamagri(@PathVariable UUID id) {
        samagriService.deleteSamagri(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/search")
    public List<Samagri> getSamagrisByName(@RequestParam String name) {
        return samagriService.getSamagrisByName(name);
    }


}
