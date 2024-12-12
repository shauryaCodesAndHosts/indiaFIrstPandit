package com.indiafirstpandit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuantityAndId {
    private UUID id;
    private int quantity;
}
