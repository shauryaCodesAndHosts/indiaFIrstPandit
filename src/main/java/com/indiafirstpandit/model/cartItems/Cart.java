package com.indiafirstpandit.model.cartItems;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartPujaItem> pujaCart ;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartProductItem> productCart;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartSamagriItem> samagriCart;
}