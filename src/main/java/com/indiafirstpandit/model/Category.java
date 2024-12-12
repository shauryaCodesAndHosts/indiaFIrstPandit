package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String image;

    @Column(length = 1000)
    private String description;
    private Integer totalItems;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category")
//    @JsonBackReference // to indicate child
    @JsonIgnore
//    @JsonManagedReference
    private List<Product> product;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

//    @Override
//    public String toString() {
//        return "Category{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
