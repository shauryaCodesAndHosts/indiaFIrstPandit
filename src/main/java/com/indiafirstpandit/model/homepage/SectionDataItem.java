package com.indiafirstpandit.model.homepage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class SectionDataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name ;
    private Integer displayOrder;
    private Boolean isActive;
    private String image;

    private String itemId;


}
