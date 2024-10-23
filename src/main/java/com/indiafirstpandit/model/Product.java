
package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String image;

    @Column(length = 1000)
    private String description;

    private BigDecimal price;

    private int stock;

    @ManyToOne
//    @JsonManagedReference //to indicate parent
    @JsonBackReference
    private Category category;  // Category of the product (e.g., flowers, utensils, etc.)

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

//    @ManyToOne
//    @JoinColumn(name = "samagri_id")
//    private Samagri samagri;  // Association to the Samagri entity
}
