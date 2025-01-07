//package com.indiafirstpandit.controller.cartcontrollers;
//
//import com.indiafirstpandit.dto.AddPujaToCartRequest;
//import com.indiafirstpandit.dto.cartDTO.CartPujaItemDto;
//import com.indiafirstpandit.model.UserPrincipal;
//import com.indiafirstpandit.model.cartItems.CartPujaItem;
//import com.indiafirstpandit.service.cartservice.CartServicePuja;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/cart/puja")
//public class CartControllerPuja {
//
//    @Autowired
//    private CartServicePuja cartServicePuja;
//
//    @PostMapping("/addPujaToCart")
//    public ResponseEntity<CartPujaItemDto> addPujaToCart(@RequestBody AddPujaToCartRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal)
//    {
//        UUID userCartId = userPrincipal.getUser().getCart().getId();
//        System.out.println(userCartId);
//        CartPujaItem cartPujaItem = cartServicePuja.addToCart(userCartId, request.getId(), request.getPandits()); //here the id is the puja id
////        return ResponseEntity.ok(cartPujaItem);
//        return ResponseEntity.ok(new CartPujaItemDto( cartPujaItem));
//    }
//
//    @PutMapping("/updatePandits")
//    public ResponseEntity<CartPujaItemDto> updatePandits(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddPujaToCartRequest request)
//    {
//        UUID userCartId = userPrincipal.getUser().getCart().getId();
//        CartPujaItem updatedCartPujaItem = cartServicePuja.updatePandits(userCartId, request.getId(), request.getPandits()) ; //here the id is the cartitem id
//        return ResponseEntity.ok(new CartPujaItemDto(updatedCartPujaItem));
//    }
//
//
//
//}
