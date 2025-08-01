//package com.indiafirstpandit.controller;
//
//import com.indiafirstpandit.dto.cartDTO.CartDto;
//import com.indiafirstpandit.model.UserPrincipal;
//import com.indiafirstpandit.model.cartItems.Cart;
//import com.indiafirstpandit.service.cartservice.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @GetMapping("/getAllProducts")
//    public CartDto getCart(@AuthenticationPrincipal UserPrincipal userPrincipal)
//    {
////        System.out.println(userPrincipal.getUser().getCart());
//        System.out.println();
//        return new CartDto( userPrincipal.getUser().getCart());
//    }
//
////    @PostMapping("/addPujaToCart")
////    public String addPujaToCart( @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody UUID pujaId)
////    {
////        UUID userCartId = userPrincipal.getUser().getCart().getId();
////        return cartService.addPujaToCart(pujaId,userCartId);
//////        return " ";
////    }
//
////    @PostMapping("/addProductToCart")
////    public String addProductToCart(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestBody UUID productId)
////    {
//////        return " ";
////        UUID userCartId = userPrincipal.getUser().getCart().getId();
////        return cartService.addProductToCart(productId,userCartId);
////    }
//
////    @PostMapping("/addSamagriToCart")
////    public String addSamagriToCart(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestBody UUID samagriId)
////    {
//////        return " ";
////        UUID userCartId = userPrincipal.getUser().getCart().getId();
////        return cartService.addSamagriToCart(samagriId,userCartId);
////    }
//
//}
