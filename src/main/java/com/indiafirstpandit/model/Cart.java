package com.indiafirstpandit.model;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany
    private List<Puja> puja ;

    @OneToMany
    private List<Product> product;

    @OneToMany
    private List<Samagri> samagri;

}