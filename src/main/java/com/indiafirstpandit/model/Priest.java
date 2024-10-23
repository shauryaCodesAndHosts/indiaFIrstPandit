package com.indiafirstpandit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Priest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String contactNumber;

    private String expertise;  // Expertise in specific types of pujas

    private String address;

}
