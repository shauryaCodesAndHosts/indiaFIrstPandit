package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.Priest;
import com.indiafirstpandit.service.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/priests")
public class PriestController {

    @Autowired
    private PriestService priestService;

    @GetMapping
    public List<Priest> getAllPriests() {
        return priestService.getAllPriests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priest> getPriestById(@PathVariable UUID id) {
        Priest priest = priestService.getPriestById(id);
        return ResponseEntity.ok(priest);
    }

    @GetMapping("/search")
    public List<Priest> searchPriests(@RequestParam(required = false) String name, @RequestParam(required = false) String expertise) {
        if (name != null) {
            return priestService.searchPriestsByName(name);
        } else if (expertise != null) {
            return priestService.searchPriestsByExpertise(expertise);
        }
        return priestService.getAllPriests();
    }

    @PostMapping
    public ResponseEntity<Priest> createPriest(@RequestBody Priest priest) {
        Priest savedPriest = priestService.savePriest(priest);
        return ResponseEntity.ok(savedPriest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriest(@PathVariable UUID id) {
        priestService.deletePriest(id);
        return ResponseEntity.noContent().build();
    }
}
