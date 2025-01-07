package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Order;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.cartItems.Cart;
import com.indiafirstpandit.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAdminService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUser(UUID id ) {
        return userRepository.findById(id).orElseThrow();
    }

    public Cart getUserCart(UUID id) {
        return userRepository.getReferenceById(id).getCart();
    }

    public List<Order> getUserOrders(UUID id) {
        return userRepository.getReferenceById(id).getOrders();
    }
}
