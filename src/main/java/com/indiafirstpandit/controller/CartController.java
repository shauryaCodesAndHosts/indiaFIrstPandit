package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @GetMapping("/getAllProducts")
    public Cart getCart(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return userPrincipal.getUser().getCart();
    }



}
