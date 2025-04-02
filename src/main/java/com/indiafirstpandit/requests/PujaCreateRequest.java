package com.indiafirstpandit.requests;

import com.indiafirstpandit.dto.samagri.SamagriDto;
import com.indiafirstpandit.model.Puja;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PujaCreateRequest {

    public PujaCreateRequest(Puja puja)
    {

        this.id = puja.getId();
        this.name = puja.getName();
        this.description = puja.getDescription();
        this.benefits = puja.getBenefits();
        this.mantra = puja.getMantra();
        this.panditNeeded = puja.getPanditNeeded();
        this.freqExtraAddedPandit = puja.getFreqExtraAddedPandit();
        this.maxPandits = puja.getMaxPandits();
        this.pricePerExtraPandit = puja.getPricePerExtraPandit();
        this.amount = puja.getAmount();
        this.image1 = puja.getImage1();
        this.image2 = puja.getImage2();
        this.image3 = puja.getImage3();

        this.pujaCategoryId = puja.getPujaCategory().getId();
//        this.samagriUUIDs = puja.getSamagri().stream().map(SamagriDto::new).toList();
    }


    private UUID id;
    private String name;
    private String description ;
    private String benefits;
    private String mantra;
    private Integer panditNeeded;
    private Integer freqExtraAddedPandit;
    private Double pricePerExtraPandit;
    private Integer maxPandits;
    private Double amount;
    private String image1;
    private String image2;
    private String image3;
    private UUID pujaCategoryId;
    private List<UUID> samagriUUIDs;
}
