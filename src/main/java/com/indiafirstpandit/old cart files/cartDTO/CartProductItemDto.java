//package com.indiafirstpandit.dto.cartDTO;
//
//import com.indiafirstpandit.dto.ProductDto;
//import com.indiafirstpandit.model.cartItems.CartProductItem;
//import lombok.Data;
//
//import java.util.UUID;
//
//@Data
//public class CartProductItemDto {
//
//    public CartProductItemDto(CartProductItem cartProductItem)
//    {
//        this.id = cartProductItem.getId();
//        this.quantity = cartProductItem.getQuantity();
//        this.productDto = new ProductDto(cartProductItem.getProduct());
//        this.discount = cartProductItem.getDiscount();
//        this.cartDto = new CartDto(cartProductItem.getCart());
//    }
//
//    private UUID id;
//    private Integer quantity;
//    private ProductDto productDto;
//    private Integer discount ;
//    private CartDto cartDto;
//
//}
