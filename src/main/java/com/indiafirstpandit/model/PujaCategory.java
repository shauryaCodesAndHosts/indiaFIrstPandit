package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PujaCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String image;

    @Column(length = 1000)
    private String description;
    private int totalItems;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pujaCategory", cascade = CascadeType.ALL)
//    @JsonBackReference // to indicate child
//    @JsonIgnore
    @JsonManagedReference
    private List<Puja> pujas;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

