package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.requests.PujaCreateRequest;
import com.indiafirstpandit.service.PujaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/puja")
public class PujaController {

    @Autowired
    private PujaService pujaService;

    @GetMapping("/getAll")
    public List<PujaDto> getAllPujas() {
        return pujaService.getAllPujas().stream().map(PujaDto::new).toList();
    }

    @PostMapping("/create")
    public ResponseEntity<PujaDto> createPuja(@RequestBody PujaCreateRequest pujaCreateRequest) {
//        System.out.println(puja);
        Puja savedPuja = pujaService.savePuja(pujaCreateRequest);
        return ResponseEntity.ok(new PujaDto(savedPuja));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePuja(@PathVariable UUID id) {
        pujaService.deletePuja(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<PujaDto> updatePuja(@RequestBody PujaCreateRequest pujaDto, @PathVariable UUID id)
    {
        ServiceStatus status = pujaService.updatePuja(id,pujaDto);
        if(status == ServiceStatus.Done)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }






    @GetMapping("/{id}")
    public ResponseEntity<PujaDto> getPujaById(@PathVariable UUID id) {
        Puja puja = pujaService.getPujaById(id);
        PujaDto pujaDto = new PujaDto(puja);
        return ResponseEntity.ok(pujaDto);
    }

    @GetMapping("/search")
    public List<Puja> getPujasByName(@RequestParam String name) {
        return pujaService.getPujasByName(name);
    }




}
