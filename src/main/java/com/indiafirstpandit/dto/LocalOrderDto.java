package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.LocalOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class LocalOrderDto {

    private UUID id ;
    private LocalDateTime orderDateTime;

    private BigDecimal totalAmount;

    private UUID userId;  // Association to the User entity (who placed the order)
    
    private List<LocalOrderItemsDto> orderItemsDto;

    private AddressDto addressDto;


    public LocalOrderDto(LocalOrder localOrder)
    {
        this.id = localOrder.getId();
        this.orderDateTime = localOrder.getOrderDateTime();
        this.totalAmount = localOrder.getTotalAmount();
        this.userId = localOrder.getUser().getId();
        this.orderItemsDto = localOrder.getOrderItems().stream().map(LocalOrderItemsDto::new).collect(Collectors.toList());
        this.addressDto = new AddressDto(localOrder.getAddress());
    }

    
}
