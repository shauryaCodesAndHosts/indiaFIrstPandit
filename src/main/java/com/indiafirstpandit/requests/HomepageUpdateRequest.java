package com.indiafirstpandit.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class HomepageUpdateRequest {
    private UUID id;
    private String sectionData;
}
