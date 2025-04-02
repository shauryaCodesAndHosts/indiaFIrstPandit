package com.indiafirstpandit.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Integer panditNeeded;  // Number of priests needed for this puja

    private Integer freqExtraAddedPandit;

    private Double pricePerExtraPandit;

    private Integer maxPandits;

    private Double amount; // Cost of the puja

    private String image1;
    private String image2;
    private String image3;

    @ManyToOne
//    @JsonManagedReference //to indicate parent
//    @JsonBackReference
    @JoinColumn(name = "puja_category_id")
    private PujaCategory pujaCategory;  // Category of the product (e.g., flowers, utensils, etc.)


    @JsonIgnore
    private String keywords;

    @ManyToMany(mappedBy = "pujas")
////    @JoinColumn(name = "samagri_id")
    private List<Samagri> samagri;  // Association to the Samagri entity



}