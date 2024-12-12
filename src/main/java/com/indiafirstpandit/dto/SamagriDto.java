package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.Samagri;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Data
@Getter
@Setter
public class SamagriDto {


    public SamagriDto(Samagri samagri)
    {
        this.id = samagri.getId();
        this.name = samagri.getName();
        if(samagri.getProducts()!=null)
        {
            this.productDtos = samagri.getProducts().stream().map(ProductDto::new).toList();
        }
        if(samagri.getPujas() != null)
        {
            this.pujaDtos = samagri.getPujas().stream().map(PujaDto::new).toList();
        }
    }


    private UUID id;
    private String name;

    private List<ProductDto> productDtos;

    private List<PujaDto> pujaDtos;

}