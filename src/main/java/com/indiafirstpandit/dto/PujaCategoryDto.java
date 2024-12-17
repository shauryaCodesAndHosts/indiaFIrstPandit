package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.PujaCategory;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PujaCategoryDto {

    public PujaCategoryDto(PujaCategory pujaCategory)
    {
        this.id = pujaCategory.getId();
        this.name=pujaCategory.getName();
        this.image=pujaCategory.getImage();
        this.description=pujaCategory.getDescription();
        this.totalItems = pujaCategory.getTotalItems();
        this.createdAt=pujaCategory.getCreatedAt();
        if(pujaCategory.getPujas() != null)
        {
            this.pujas = pujaCategory.getPujas().stream().map(PujaDto::new).toList();
        }
    }

    private UUID id;
    private String name ;
    private String image;
    private String description ;
    private Integer totalItems;
    private LocalDateTime createdAt;
    private List<PujaDto> pujas;

}
