//package com.indiafirstpandit.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Component
//@Entity
//@Table(name = "ordersPlaced")
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//    private LocalDateTime orderDate;
//
//    private BigDecimal totalAmount;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;  // Association to the User entity (who placed the order)
//
//    @OneToOne
//    @JoinColumn(name = "puja_id")
//    private Puja puja;  // Optional: Puja booked in this order (if any)
//
//    @ManyToOne
//    @JoinColumn(name = "samagri_id")
//    private Samagri samagri;  // Optional: Samagri ordered in this order (if any)
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;  // Optional: Product ordered in this order (if any)
//}