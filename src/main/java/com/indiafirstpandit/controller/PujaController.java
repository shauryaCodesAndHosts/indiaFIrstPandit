package com.indiafirstpandit.controller;

import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.service.PujaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pujas")
public class PujaController {

    @Autowired
    private PujaService pujaService;

    @GetMapping
    public List<Puja> getAllPujas() {
        return pujaService.getAllPujas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puja> getPujaById(@PathVariable UUID id) {
        Puja puja = pujaService.getPujaById(id);
        return ResponseEntity.ok(puja);
    }

    @GetMapping("/search")
    public List<Puja> getPujasByName(@RequestParam String name) {
        return pujaService.getPujasByName(name);
    }

    @PostMapping
    public ResponseEntity<Puja> createPuja(@RequestBody Puja puja) {
        System.out.println(puja);
        Puja savedPuja = pujaService.savePuja(puja);
        return ResponseEntity.ok(savedPuja);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuja(@PathVariable UUID id) {
        pujaService.deletePuja(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Puja> updatePuja(@RequestBody Puja puja, @PathVariable UUID id)
    {
        ServiceStatus status = pujaService.updatePuja(id,puja);
        if(status == ServiceStatus.Done)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

}
