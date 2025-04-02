package com.indiafirstpandit.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class OptionsResponse {

    private String key;
    private Integer amount;
    private String name;
    private String order_id;
    private String description;
    private Integer timeout; // Added timeout field
    private Prefill prefill; // Added prefill object

    @Data
    @Builder
    @Getter
    @Setter
    public static class Prefill {
        private String contact;
        private String email;
    }


}
