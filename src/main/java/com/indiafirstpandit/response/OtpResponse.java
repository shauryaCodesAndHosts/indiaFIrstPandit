package com.indiafirstpandit.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpResponse {
    private String OtpToken;
    private String Message;
}
