
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

    //(mappedBy = "samagri", cascade = CascadeType.ALL)
    @OneToMany
    private List<Product> products;  // List of products in the samagri

    @OneToMany(mappedBy = "samagri", cascade = CascadeType.ALL)
    private List<Puja> pujas;  // List of pujas associated with this samagri
}
