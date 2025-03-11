package com.indiafirstpandit.response;

import com.indiafirstpandit.enums.VerificationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Getter
@Setter
@Builder
public class RegisterResponse {
    private VerificationStatus status;
    private String jwtToken;
    private String refreshToken;
    private Instant expiry;
    private String email;
}
