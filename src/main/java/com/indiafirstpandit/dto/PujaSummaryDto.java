package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.Puja;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PujaSummaryDto {
    public PujaSummaryDto(Puja puja) {
        this.id = puja.getId();
        this.name = puja.getName();
    }

    private UUID id;
    private String name;
}
