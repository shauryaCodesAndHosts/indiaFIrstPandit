package com.indiafirstpandit.controller;

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

    @GetMapping
    public List<Samagri> getAllSamagris() {
        return samagriService.getAllSamagris();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Samagri> getSamagriById(@PathVariable UUID id) {
        Samagri samagri = samagriService.getSamagriById(id);
        return ResponseEntity.ok(samagri);
    }

    @GetMapping("/search")
    public List<Samagri> getSamagrisByName(@RequestParam String name) {
        return samagriService.getSamagrisByName(name);
    }

    @PostMapping
    public ResponseEntity<Samagri> createSamagri(@RequestBody Samagri samagri) {
        Samagri savedSamagri = samagriService.saveSamagri(samagri);
        return ResponseEntity.ok(savedSamagri);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSamagri(@PathVariable UUID id) {
        samagriService.deleteSamagri(id);
        return ResponseEntity.noContent().build();
    }
}
