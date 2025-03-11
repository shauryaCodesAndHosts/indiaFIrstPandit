package com.indiafirstpandit.response;

import com.indiafirstpandit.model.RefreshToken;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;


@Data
@Builder
public class JwtResponse {
    private String jwtToken;
    private String refreshToken;
    private Instant expiry;
    private String userName;
    private String message;
}
