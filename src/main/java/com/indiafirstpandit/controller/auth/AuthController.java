package com.indiafirstpandit.controller.auth;

import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.enums.VerificationStatus;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.requests.*;
import com.indiafirstpandit.response.JwtResponse;
import com.indiafirstpandit.response.OtpResponse;
import com.indiafirstpandit.response.RegisterResponse;
import com.indiafirstpandit.service.JwtService;
import com.indiafirstpandit.service.UserService;
import com.indiafirstpandit.service.auth.ForgotPasswordService;
import com.indiafirstpandit.service.auth.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = userService.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<RegisterResponse> verify(@RequestBody VerifyRequest verifyRequest) {
        return ResponseEntity.ok(userService.verify(verifyRequest.getEmail(), verifyRequest.getOtp()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {

        return userService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshJwtToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        System.out.println(refreshTokenRequest);
        if(refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken()))
        {
            return ResponseEntity.ok(refreshTokenService.refreshJwtToken(refreshTokenRequest.getRefreshToken()));
        }
        return new ResponseEntity<>(JwtResponse.builder().message("Wrong Token")
                .build(),HttpStatus.FORBIDDEN);
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<?> resendOtp(@RequestBody EmailRequest emailRequest)
    {
        return ResponseEntity.ok(userService.resendOtp(emailRequest.getEmail()));
    }

    @PostMapping("/forgotPassword/email")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody ForgotPasswordEmailRequest forgotPasswordEmailRequest)
    {
        OtpResponse otpResponse = forgotPasswordService.sendOtp(forgotPasswordEmailRequest.getEmail());
        if(otpResponse.getOtpToken().isEmpty())
        {
            return new ResponseEntity<>(otpResponse,HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(otpResponse);
    }

    @PostMapping("/forgotPassword/validateOtp")
    public ResponseEntity<?> validateOtp(@RequestBody ForgotPasswordOtpRequest request)
    {
        OtpResponse response = forgotPasswordService.validateOtp(request.getEmail(),request.getOtp(),request.getToken());
//        if (status == V)
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgotPassword/newPassword")
    public ResponseEntity<?> newPassword(@RequestBody ForgotPasswordNewPassword request)
    {
        ServiceStatus status = forgotPasswordService.newPassword(request.getEmail(), request.getToken(), request.getNewPassword());
        return ResponseEntity.ok(status);
    }

}











































