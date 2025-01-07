package com.indiafirstpandit.dto.cartDTO;

import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.model.cartItems.CartPujaItem;
import lombok.Data;

import java.util.UUID;

@Data
public class CartPujaItemDto {

    public CartPujaItemDto(CartPujaItem cartPujaItem)
    {
        this.id = cartPujaItem.getId();
        this.pujaDto = new PujaDto( cartPujaItem.getPuja() );
        this.panditAdded = cartPujaItem.getPanditAdded();
        this.discount = cartPujaItem.getDiscount();
        this.cartDto = new CartDto(cartPujaItem.getCart());
    }

    private UUID id;
    private int panditAdded;
    private PujaDto pujaDto;
    private int discount;
    private CartDto cartDto;

}
