package com.indiafirstpandit.controller;

import com.indiafirstpandit.enums.UserRoles;
import com.indiafirstpandit.model.Order;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.model.cartItems.Cart;
import com.indiafirstpandit.service.UserAdminService;
import com.indiafirstpandit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/user")
public class AdminUserControllers {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping("/getAll")
    public List<User> getAllUsers(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getUser().getRole() == UserRoles.Admin)
            return userAdminService.getAllUsers();
        else
            return null;
    }

    @GetMapping("/{id}")
    public User getUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable UUID id) {
        if (userPrincipal.getUser().getRole() == UserRoles.Admin)
            return userAdminService.getUser(id);
        else
            return null;
    }

    @GetMapping("/{id}/cart")
    public Cart getCartOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable UUID id) {
        if (userPrincipal.getUser().getRole() == UserRoles.Admin)
            return userAdminService.getUserCart(id);
        else
            return null;
    }

    @GetMapping("/{id}/orders")
    public List<Order> getUserOrders(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable UUID id)
    {
        if (userPrincipal.getUser().getRole()==UserRoles.Admin)
        {
             return userAdminService.getUserOrders(id);
        }
        else
        {
            return null;
        }
    }


}
