package com.indiafirstpandit.model.cartItems;

import com.indiafirstpandit.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private int quantity;

    @OneToOne
    private Product product;

    private int discount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
