package com.indiafirstpandit.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
