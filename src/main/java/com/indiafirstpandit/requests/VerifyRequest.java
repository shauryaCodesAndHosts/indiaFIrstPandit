package com.indiafirstpandit.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerifyRequest {
        private String email;
        private String otp;
}
