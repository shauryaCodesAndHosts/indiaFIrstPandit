package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.Address;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.repo.UserRepository;
import com.indiafirstpandit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAddresses")
    public List<Address> getAddresses(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return userPrincipal.getUser().getAddresses();
    }

    @PostMapping("/addAddress")
    public List<Address> addAddress(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Address address)
    {
        address.setUser(userPrincipal.getUser());
        System.out.println("1 ->"+address);
        List<Address> addresses = userPrincipal.getUser().getAddresses();
        System.out.println( "2 ->"+ addresses);
        addresses.add(address);
        System.out.println( "3 ->"+ addresses);
        userPrincipal.getUser().setAddresses(addresses);
        System.out.println( "4 ->"+ userPrincipal);
        userService.saveUser(userPrincipal.getUser());
        return userService.getUserById(userPrincipal.getUser().getId()).getAddresses();
    }

}
