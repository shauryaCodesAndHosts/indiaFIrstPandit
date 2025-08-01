//package com.indiafirstpandit.dto.cartDTO;
//
//import com.indiafirstpandit.dto.samagri.SamagriDto;
//import com.indiafirstpandit.model.cartItems.CartSamagriItem;
//import lombok.Data;
//
//import java.util.UUID;
//
//@Data
//public class CartSamagriItemDto {
//
//    public CartSamagriItemDto(CartSamagriItem cartSamagriItem)
//    {
//        this.id = cartSamagriItem.getId();
//        this.quantity = cartSamagriItem.getQuantity();
//        this.samagriDto = new SamagriDto(cartSamagriItem.getSamagri());
//        this.discount = cartSamagriItem.getDiscount();
//        this.cartDto = new CartDto(cartSamagriItem.getCart());
//    }
//
//    private UUID id;
//    private Integer quantity;
//    private SamagriDto samagriDto;
//    private Integer discount;
//    private CartDto cartDto;
//}
