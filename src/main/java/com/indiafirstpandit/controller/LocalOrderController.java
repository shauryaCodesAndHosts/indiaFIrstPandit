package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.LocalOrder;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.requests.JustUUID;
import com.indiafirstpandit.response.OptionsResponse;
import com.indiafirstpandit.service.LocalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")  // Allow all origins and headers
public class LocalOrderController {

    @Autowired
    private LocalOrderService localOrderService;

    @GetMapping
    public List<LocalOrder> getAllOrders() {
        return localOrderService.getAllOrders();
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OptionsResponse> placeOrder(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody JustUUID id)
    {
//
        User currentUser = userPrincipal.getUser();
        Cart userCart = currentUser.getCart();
        if (userCart == null || userCart.getCartItems().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return an error response if the cart is empty
        }

        return ResponseEntity.ok(localOrderService.placeOrder(currentUser, id.getId()));
//        return null;
    }






    @GetMapping("/{id}")
    public ResponseEntity<LocalOrder> getOrderById(@PathVariable UUID id) {
        LocalOrder order = localOrderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public List<LocalOrder> getOrdersByUserId(@PathVariable UUID userId) {
        return localOrderService.getOrdersByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<LocalOrder> createOrder(@RequestBody LocalOrder order) {
        LocalOrder savedOrder = localOrderService.saveOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        localOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
