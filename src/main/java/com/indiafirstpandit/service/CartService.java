package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Puja> getAllPujas(UUID id)
    {
        Cart cart= cartRepository.findById(id).get();
        return cart.getPuja();
    }

    public List<Product> getAllProducts(UUID id)
    {
        return cartRepository.findById(id).get().getProduct();
    }

    public List<Samagri> getAllSamagri(UUID id)
    {
        return cartRepository.findById(id).get().getSamagri();
    }


}
