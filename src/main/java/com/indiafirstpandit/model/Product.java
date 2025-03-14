
package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
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

    private Double price;

    private Integer stock;

//    @ManyToOne
////    @JsonManagedReference //to indicate parent
////    @JsonBackReference
//    private Category category;  // Category of the product (e.g., flowers, utensils, etc.)

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    (cascade = CascadeType.ALL)

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                '}';
//    }

//    @ManyToOne
//    @JoinColumn(name = "samagri_id")
//    private Samagri samagri;  // Association to the Samagri entity
}
