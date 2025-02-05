package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.dto.samagri.SamagriAddId;
import com.indiafirstpandit.dto.samagri.SamagriInformation;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.repo.ProductRepository;
import com.indiafirstpandit.repo.PujaRepository;
import com.indiafirstpandit.repo.SamagriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SamagriService {

    @Autowired
    private SamagriRepository samagriRepository;

    @Autowired
    private PujaRepository pujaRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Samagri> getAllSamagris() {
        return samagriRepository.findAll();
    }

    public Samagri getSamagriById(UUID id) {
        return samagriRepository.findById(id).orElseThrow(() -> new RuntimeException("Samagri not found"));
    }

    public List<Samagri> getSamagrisByName(String name) {
        return samagriRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Samagri> getSamagrisByProduct(UUID productId) {
        return samagriRepository.findByProductsId(productId);
    }

    public Samagri saveSamagri(Samagri samagri) {
        return samagriRepository.save(samagri);
    }

    public void deleteSamagri(UUID id) {
        samagriRepository.deleteById(id);
    }

    public ServiceStatus editSamagriInformation(UUID id,SamagriInformation samagriInformation) {
        if(samagriRepository.existsById(id)) {
            Samagri samagri = samagriRepository.findById(id).orElse(new Samagri());
            samagri.setName(samagriInformation.getName());
//            samagri.setStock(sa);
            samagriRepository.save(samagri);
            return ServiceStatus.Done;
        }
        else {
            return ServiceStatus.Not_Found;
        }

    }

    public ServiceStatus addPuja(UUID id, SamagriAddId samagriAddId) {

        if(samagriRepository.existsById(id))
        {
            Samagri samagri = samagriRepository.findById(id).orElse(new Samagri());
            System.out.println(samagri);
            List<Puja> pujas = samagri.getPujas();
            Puja puja = pujaRepository.findById(samagriAddId.getId()).orElse(new Puja());
//            puja.setSamagri(samagri);
            pujas.add(puja);
            samagriRepository.save(samagri);
            return ServiceStatus.Done;

        }
        else {
            return ServiceStatus.Not_Found;
        }



    }

    public ServiceStatus addProduct(UUID id, SamagriAddId samagriAddId) {

        if(samagriRepository.existsById(id))
        {
            Samagri samagri = samagriRepository.findById(id).orElse(new Samagri());
            List<Product> products = samagri.getProducts();
            Product product = productRepository.findById(samagriAddId.getId()).orElse(new Product());
            products.add(product);
            samagriRepository.save(samagri);
            return ServiceStatus.Done;
        }
        else {
            return ServiceStatus.Not_Found;
        }

    }
}
