package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;



@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PujaDto {

    public PujaDto(Puja puja)
    {

        this.id = puja.getId();
        this.name = puja.getName();
        this.description = puja.getDescription();
        this.benefits = puja.getBenefits();
        this.mantra = puja.getMantra();
        this.panditNeeded = puja.getPanditNeeded();
        this.freqExtraAddedPandit = puja.getFreqExtraAddedPandit();
        this.amount = puja.getAmount();
        this.image1 = puja.getImage1();
        this.image2 = puja.getImage2();
        this.image3 = puja.getImage3();

        this.pujaCategoryId = puja.getPujaCategory().getId();
//        if(puja.getSamagri()!=null) {
//            this.samagriId = puja.getSamagri().getId();
//        }
//        else
//        {
//            this.samagriId = null;
//        }
    }


    private UUID id;
    private String name;
    private String description ;
    private String benefits;
    private String mantra;
    private Integer panditNeeded;
    private Integer freqExtraAddedPandit;
    private BigDecimal amount;
    private String image1;
    private String image2;
    private String image3;
    private UUID pujaCategoryId;
//    private UUID samagriId;
}
