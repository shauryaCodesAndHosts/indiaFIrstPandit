package com.indiafirstpandit.model.cartItems;

import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.Puja;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartPujaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//    private CartItemType cartItemType;

    private int panditAdded;

    @ManyToOne
    private Puja puja;

    private int discount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
