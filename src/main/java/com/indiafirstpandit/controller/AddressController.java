package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.AddressDto;
import com.indiafirstpandit.model.Address;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.repo.UserRepository;
import com.indiafirstpandit.service.AddressService;
import com.indiafirstpandit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAddresses")
    public List<AddressDto> getAddresses(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return addressService.getAddresses(userPrincipal.getUser()).stream().map(AddressDto::new).collect(Collectors.toList());
    }

    @PostMapping("/addAddress")
    public List<AddressDto> addAddress(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddressDto addressDto)
    {
        return addressService.addAddress(userPrincipal.getUser(), addressDto).stream().map(AddressDto::new).collect(Collectors.toList());
    }

}
//        address.setUser(userPrincipal.getUser());
//        System.out.println("1 ->"+address);
//        List<Address> addresses = userPrincipal.getUser().getAddresses();
//        System.out.println( "2 ->"+ addresses);
//        addresses.add(address);
//        System.out.println( "3 ->"+ addresses);
//        userPrincipal.getUser().setAddresses(addresses);
//        System.out.println( "4 ->"+ userPrincipal);
//        userService.saveUser(userPrincipal.getUser());
//        return userService.getUserById(userPrincipal.getUser().getId()).getAddresses();
