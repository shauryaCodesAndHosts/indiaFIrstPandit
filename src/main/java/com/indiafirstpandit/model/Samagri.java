
package com.indiafirstpandit.model;

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
public class Samagri {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Double price;

    private Integer stock;

    private String image;

    //(mappedBy = "samagri", cascade = CascadeType.ALL)
    @ManyToMany
    private List<Product> products;  // List of products in the samagri

    @ManyToMany
    private List<Puja> pujas;  // List of pujas associated with this samagri
}
