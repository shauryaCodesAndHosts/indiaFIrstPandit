package com.indiafirstpandit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "ordersPlaced")
public class LocalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime orderDateTime;

    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Association to the User entity (who placed the order)


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<LocalOrderItem> orderItems;

    @ManyToOne
    private Address address;



    @PrePersist
    protected void onCreate()
    {
        this.orderDateTime = LocalDateTime.now();
    }
}