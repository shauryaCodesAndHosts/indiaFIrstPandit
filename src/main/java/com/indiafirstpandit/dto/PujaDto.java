package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;

import java.math.BigDecimal;
import java.util.UUID;




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

        this.pujaCategoryDto = new PujaCategoryDto(puja.getPujaCategory());
        this.samagriDto = new SamagriDto(puja.getSamagri());
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
    private PujaCategoryDto pujaCategoryDto;
    private SamagriDto samagriDto;
}
