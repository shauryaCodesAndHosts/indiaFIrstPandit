package com.indiafirstpandit.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordOtpRequest {
    private String email;
    private String otp;
    private String token;
}
