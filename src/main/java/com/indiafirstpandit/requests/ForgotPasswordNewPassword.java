package com.indiafirstpandit.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordNewPassword {
    private String newPassword;
    private String email;
    private String token;
}
