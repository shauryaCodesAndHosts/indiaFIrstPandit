package com.indiafirstpandit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RazorpayOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//    @OneToOne
//    private LocalOrder localOrder;

    private String order_id;
    private Integer amount ;
    private String name;
    private String description;
    private String razorpay_payment_id;
    private String razorpay_order_id;
    private String razorpay_signature;

}
