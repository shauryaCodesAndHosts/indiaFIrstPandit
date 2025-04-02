package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indiafirstpandit.enums.OrderItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private LocalOrder order;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderItemType orderItemType;

    private Integer quantity;

    private Double price;

    private UUID itemId;

    private String itemName;


}
