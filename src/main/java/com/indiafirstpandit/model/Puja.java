package com.indiafirstpandit.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Puja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String benefits;

    @Column(length = 1000)
    private String mantra;

    private int panditNeeded;  // Number of priests needed for this puja

    private BigDecimal amount; // Cost of the puja

    private String image1;
    private String image2;
    private String image3;

    @ManyToOne
    @JoinColumn(name = "samagri_id")
    private Samagri samagri;  // Association to the Samagri entity
}