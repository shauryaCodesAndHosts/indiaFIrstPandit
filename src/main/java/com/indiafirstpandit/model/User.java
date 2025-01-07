package com.indiafirstpandit.model;

import com.indiafirstpandit.enums.UserRoles;
import com.indiafirstpandit.model.cartItems.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private String password;  // Encrypted password

    private double latitude;
    private double longitude;

//    @OneToMany
//    private Order orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;  // List of orders placed by the user

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    private UserRoles role;

}
