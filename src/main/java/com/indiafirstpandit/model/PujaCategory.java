package com.indiafirstpandit.model;

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
public class PujaCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String image;

//    @Column(length = 1000)
    private String description;
    private Integer totalItems;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pujaCategory", cascade = CascadeType.ALL)
//    @JsonBackReference // to indicate child
//    @JsonIgnore
//    @JsonManagedReference
    private List<Puja> pujas;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }


}

