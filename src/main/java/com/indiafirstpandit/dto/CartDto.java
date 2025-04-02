package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.Cart;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    public CartDto(Cart cart)
    {
        this.id = cart.getId();
        if(!cart.getCartItems().isEmpty()) {
            this.cartItemDtos = cart.getCartItems().stream().map(CartItemDto::new).toList();
        }
        this.totalCartPrice = cart.getTotalCartPrice();
        this.gst= cart.getGst();
    }

    private UUID id;
    private List<CartItemDto> cartItemDtos ;
    private Double totalCartPrice ;
    private Double gst;
}