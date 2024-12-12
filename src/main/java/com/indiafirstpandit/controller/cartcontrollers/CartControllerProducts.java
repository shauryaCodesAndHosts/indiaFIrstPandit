package com.indiafirstpandit.controller.cartcontrollers;

import com.indiafirstpandit.dto.QuantityAndId;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.model.cartItems.CartProductItem;
import com.indiafirstpandit.service.cartservice.CartServiceProducts;
import com.indiafirstpandit.service.cartservice.CartServicePuja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/cart/products")
public class CartControllerProducts {

    @Autowired
    private CartServiceProducts cartServiceProducts;

    @PostMapping("/addProduct")
    public ResponseEntity<CartProductItem> addProduct(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody  QuantityAndId quantityAndId)
    {
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        CartProductItem cartProductItem = cartServiceProducts.addProductToCart( userCartId, quantityAndId.getQuantity(), quantityAndId.getId() );
        return ResponseEntity.ok(cartProductItem);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<CartProductItem> updateQuantity(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody QuantityAndId quantityAndId)
    {
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        CartProductItem cartProductItem = cartServiceProducts.updateQuantity(userCartId, quantityAndId.getQuantity(), quantityAndId.getId());
        return ResponseEntity.ok(cartProductItem);
    }
}
