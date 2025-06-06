package com.indiafirstpandit.dto.samagri;

import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.dto.PujaSummaryDto;
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
        this.price = samagri.getPrice();
        this.stock = samagri.getStock();
        if(samagri.getProducts()!=null)
        {
            this.productDtos = samagri.getProducts().stream().map(ProductDto::new).toList();
        }
        if(samagri.getPujas() != null)
        {
            this.pujaSummaryDtos = samagri.getPujas().stream().map(PujaSummaryDto::new).toList();
        }
        this.image = samagri.getImage();
    }


    private UUID id;
    private String name;
    private Double price;
    private Integer stock;
    private List<ProductDto> productDtos;
    private String image;
    private List<PujaSummaryDto> pujaSummaryDtos;

}
