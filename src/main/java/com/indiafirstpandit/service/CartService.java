package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import com.indiafirstpandit.repo.CartRepository;
import com.indiafirstpandit.repo.ProductRepository;
import com.indiafirstpandit.repo.PujaRepository;
import com.indiafirstpandit.repo.SamagriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PujaRepository pujaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SamagriRepository samagriRepository;

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


    public String addPujaToCart(UUID pujaId, UUID userId) {
        Puja newPuja = pujaRepository.getReferenceById(pujaId);
        Cart userCart = cartRepository.getReferenceById(userId);
        List<Puja> pujas = userCart.getPuja();
        pujas.add(newPuja);
        userCart.setPuja(pujas);
        cartRepository.save(userCart);
        return "200";
    }

    public String addProductToCart(UUID productId, UUID userId) {
        Product newProduct = productRepository.getReferenceById(productId);
        Cart userCart = cartRepository.getReferenceById(userId);
        List<Product> products= userCart.getProduct();
        products.add(newProduct);
        userCart.setProduct(products);
        cartRepository.save(userCart);
        return "200";
    }

    public String addSamagriToCart(UUID samagriId, UUID userId) {
        Samagri newSamagri = samagriRepository.getReferenceById(samagriId);
        Cart userCart = cartRepository.getReferenceById(userId);
        List<Samagri> samagris= userCart.getSamagri();
        samagris.add(newSamagri);
        userCart.setSamagri(samagris);
        cartRepository.save(userCart);
        return "200";

    }
}
