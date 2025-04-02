package com.indiafirstpandit.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
public class UsersDeleted {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String reason;
    private LocalDateTime orderDateTime;

    private String name;
    private String emailAddress;
    private String phoneNumber ;
    private Integer ordersPlaced;

    @PrePersist
    protected void onCreate()
    {
        this.orderDateTime = LocalDateTime.now();
    }

}
