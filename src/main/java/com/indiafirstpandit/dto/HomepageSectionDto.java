package com.indiafirstpandit.dto;

import com.indiafirstpandit.enums.HomepageSectionType;
import lombok.Data;

import java.util.UUID;

@Data
public class HomepageSectionDto {

    private UUID id;
    private HomepageSectionType homepageSectionType;



}
