package com.indiafirstpandit.dto;

import com.indiafirstpandit.enums.OrderItemType;
import com.indiafirstpandit.model.LocalOrderItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.swing.*;
import java.util.UUID;

@Data
public class LocalOrderItemsDto {

    public LocalOrderItemsDto(LocalOrderItem localOrderItem)
    {
        this.id= localOrderItem.getId();
        this.orderId = localOrderItem.getOrder().getId();
        this.orderItemType = localOrderItem.getOrderItemType();
        this.quantity = localOrderItem.getQuantity();
        this.price = localOrderItem.getPrice();
        this.itemId = localOrderItem.getItemId();
        this.itemName = localOrderItem.getItemName();
    }

    private UUID id;
    private UUID orderId;
    private OrderItemType orderItemType;
    private Integer quantity;
    private Double price ;
    private UUID itemId;
    private String itemName;


}
