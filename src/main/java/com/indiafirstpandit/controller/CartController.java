package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getAllProducts")
    public Cart getCart(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return userPrincipal.getUser().getCart();
    }

    @PostMapping("/addPujaToCart")
    public String addPujaToCart( @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody UUID pujaId)
    {
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        return cartService.addPujaToCart(pujaId,userCartId);
//        return " ";
    }

    @PostMapping("/addProductToCart")
    public String addProductToCart(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestBody UUID productId)
    {
//        return " ";
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        return cartService.addProductToCart(productId,userCartId);
    }

    @PostMapping("/addSamagriToCart")
    public String addSamagriToCart(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestBody UUID samagriId)
    {
//        return " ";
        UUID userCartId = userPrincipal.getUser().getCart().getId();
        return cartService.addSamagriToCart(samagriId,userCartId);
    }

}
