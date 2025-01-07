package com.indiafirstpandit.dto.cartDTO;

import com.indiafirstpandit.model.cartItems.Cart;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartDto {

    public CartDto(Cart cart)
    {

        this.id = cart.getId();
        this.pujaCart = cart.getPujaCart().stream().map(CartPujaItemDto::new).toList();
        this.productCart = cart.getProductCart().stream().map(CartProductItemDto::new).toList();
        this.samagricart = cart.getSamagriCart().stream().map(CartSamagriItemDto::new).toList();

    }

    private UUID id;
    private List<CartPujaItemDto> pujaCart;
    private List<CartProductItemDto> productCart;
    private List<CartSamagriItemDto> samagricart;


}
