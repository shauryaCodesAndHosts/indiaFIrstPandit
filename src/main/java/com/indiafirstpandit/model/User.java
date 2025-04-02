package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indiafirstpandit.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
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

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LocalOrder> orders;  // List of orders placed by the user

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;


    private boolean isVerified;

    private UserRoles role;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private RefreshToken refreshToken;

//    private String otp;

    @OneToOne
    @JsonIgnore
    private Otp otp;


}
