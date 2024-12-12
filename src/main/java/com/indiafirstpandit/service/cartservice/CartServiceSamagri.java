package com.indiafirstpandit.service.cartservice;

import com.indiafirstpandit.model.cartItems.Cart;
import com.indiafirstpandit.model.cartItems.CartSamagriItem;
import com.indiafirstpandit.repo.SamagriRepository;
import com.indiafirstpandit.repo.cartRepository.CartRepoSamagri;
import com.indiafirstpandit.repo.cartRepository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CartServiceSamagri {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SamagriRepository samagriRepository;

    @Autowired
    private CartRepoSamagri cartRepoSamagri;

    public CartSamagriItem addSamagriToCart(UUID userCartId, int quantity, UUID id) {
        Cart userCart = cartRepository.getReferenceById(userCartId);
        System.out.println("Samagri problem ->>> user cart  "+userCart);
        CartSamagriItem cartSamagriItem = new CartSamagriItem();
        cartSamagriItem.setSamagri(samagriRepository.getReferenceById(id));
        System.out.println("Samagri problem ->>> samamgri  "+samagriRepository.getReferenceById(id));
        cartSamagriItem.setQuantity(quantity);
        cartSamagriItem.setDiscount(0);
        System.out.println("Samagri problem ->>> cart samagri item  "+ cartSamagriItem);
        cartRepoSamagri.save(cartSamagriItem);
        userCart.getSamagriCart().add(cartSamagriItem);
        cartRepository.save(userCart);
        return cartSamagriItem;
    }

    public CartSamagriItem updateQuantity(UUID userCartId, int quantity, UUID id) {
        Cart userCart = cartRepository.getReferenceById(userCartId);
        List<CartSamagriItem> samagriInCart = userCart.getSamagriCart();
        Optional<CartSamagriItem> samamgriToBeUpdated = samagriInCart.stream().filter(item -> item.getId().equals(id))
                .findFirst();
        if(samamgriToBeUpdated.isPresent())
        {
            CartSamagriItem cartSamagriItem = samamgriToBeUpdated.get();
            cartSamagriItem.setQuantity(quantity);
            cartRepoSamagri.save(cartSamagriItem);
            return cartSamagriItem;
        }

        return new CartSamagriItem();
    }
}