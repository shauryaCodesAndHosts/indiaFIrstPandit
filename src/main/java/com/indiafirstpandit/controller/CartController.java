package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.CartDto;
import com.indiafirstpandit.dto.CartItemDto;
import com.indiafirstpandit.dto.Quantity;
import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getCart")
    public ResponseEntity<CartDto> getEverything(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return ResponseEntity.ok(cartService.getCart(userPrincipal.getUser()));
    }

    @PostMapping("/addToCart")
    public HttpStatus addToCart(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CartItemDto cartItemDto)
    {
        Cart cart = cartService.addToCart(userPrincipal.getUser(),cartItemDto);
        if (cart.getCartItems()==null)
        {
            return HttpStatus.FORBIDDEN;
        }
        return HttpStatus.CREATED;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CartDto> editCartItem(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Quantity quantity, @PathVariable UUID id)
    {
        Cart cart = cartService.updateCart(userPrincipal.getUser(), id, quantity);
        if (cart.getCartItems()==null)
        {
//            return HttpStatus.NOT_ACCEPTABLE;
            return ResponseEntity.ok(cartService.getCart(userPrincipal.getUser()));

        }
//        return HttpStatus.CREATED;
        return ResponseEntity.ok(cartService.getCart(userPrincipal.getUser()));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable UUID id)
    {
        cartService.deleteCartItem(userPrincipal.getUser(),id);
        return ResponseEntity.noContent().build();
    }

}
