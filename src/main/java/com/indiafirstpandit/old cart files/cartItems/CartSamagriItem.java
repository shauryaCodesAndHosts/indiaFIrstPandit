//package com.indiafirstpandit.model.cartItems;
//
//import com.indiafirstpandit.model.Product;
//import com.indiafirstpandit.model.Samagri;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.UUID;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CartSamagriItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//
//    private int quantity;
//
//    @ManyToOne
//    private Samagri samagri;
//
//    private int discount;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
//}
