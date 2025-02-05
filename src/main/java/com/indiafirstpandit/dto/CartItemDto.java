package com.indiafirstpandit.dto;

import com.indiafirstpandit.enums.CartItemType;
import com.indiafirstpandit.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    public CartItemDto(CartItem cartItem)
    {
        this.id = cartItem.getId();
        this.itemId = cartItem.getItemId();
        this.cartItemType = cartItem.getCartItemType();
        this.quantity = cartItem.getQuantity();
        this.discount = cartItem.getDiscount();
        this.finalPrice = cartItem.getFinalPrice();
        this.cartId = cartItem.getCart().getId();
        this.addedAt = cartItem.getAddedAt();
    }

    private UUID id;
    private UUID itemId;
    private CartItemType cartItemType;
    private Integer quantity;
    private Integer discount;
    private Double finalPrice;
    private UUID cartId;
    private LocalDateTime addedAt;
}
