package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indiafirstpandit.enums.CartItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID itemId; // The UUID of the item (Product, Puja, or Samagri)

    @Enumerated(EnumType.STRING)
    private CartItemType cartItemType; // Enum to specify the type of item (PRODUCT, PUJA, SAMAGRI)

    private Integer quantity;

    private Integer discount; // Discount applied to the item (if any)

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart; // Reference to the parent cart

    private Double finalPrice;

    private LocalDateTime addedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
    }

}
