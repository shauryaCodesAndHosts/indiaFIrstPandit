package com.indiafirstpandit.controller.cartcontrollers;

import com.indiafirstpandit.dto.QuantityAndId;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.model.cartItems.CartSamagriItem;
import com.indiafirstpandit.service.cartservice.CartServicePuja;
import com.indiafirstpandit.service.cartservice.CartServiceSamagri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart/samagri")
public class CartControllerSamagri {

    @Autowired
    private CartServiceSamagri cartServiceSamagri;

    @PostMapping("/addSamagriToCart")
    public ResponseEntity<CartSamagriItem> addSamagriToCart(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody QuantityAndId quantityAndId)
    {
        System.out.println("hello world");
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        System.out.println(userCartId);
        CartSamagriItem cartSamagriItem = cartServiceSamagri.addSamagriToCart(userCartId, quantityAndId.getQuantity(), quantityAndId.getId() );
        return ResponseEntity.ok(cartSamagriItem);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<CartSamagriItem> updateQuantity(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody QuantityAndId quantityAndId)
    {
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        CartSamagriItem cartSamagriItem = cartServiceSamagri.updateQuantity(userCartId, quantityAndId.getQuantity(), quantityAndId.getId());
        return ResponseEntity.ok(cartSamagriItem);
    }


}
