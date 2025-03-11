package com.indiafirstpandit.service.auth;

import com.indiafirstpandit.dto.mailDto.SimpleMail;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.enums.VerificationStatus;
import com.indiafirstpandit.model.Otp;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.repo.OtpRepository;
import com.indiafirstpandit.repo.UserRepository;
import com.indiafirstpandit.response.OtpResponse;
import com.indiafirstpandit.service.JwtService;
import com.indiafirstpandit.service.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OtpRepository otpRepository;

    public OtpResponse sendOtp(String email) {
        User existingUser = userRepository.findByEmail(email).orElse(new User());
        if(existingUser.getEmail() != null)
        {
            String token = jwtService.generateToken(email);
             Otp otpOb = Otp.builder()
                     .token(token)
                     .otp(generateOTP())
                     .expiry(Instant.now().plusMillis(1000*60*5))
                     .build();
             existingUser.setOtp(otpOb);
            otpRepository.save(otpOb);
            userRepository.save(existingUser);
             sendVerificationMail(email,otpOb.getOtp());

             return OtpResponse.builder()
                     .OtpToken(token)
                     .Message("Otp sent successfully")
                     .build();
        }
        else {
            return OtpResponse.builder().OtpToken("").Message("Create account first")
                    .build();
        }

    }




    private String generateOTP(){
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationMail(String email, String otp)
    {
        SimpleMail simpleMail = new SimpleMail();
        simpleMail.setSender("IndiaFirstPandit");
        simpleMail.setRecipient(email);
        simpleMail.setSubject("Email verification");
        simpleMail.setBody("Your verification otp is " + otp);
        mailSenderService.sendEmail(simpleMail);
    }

    public OtpResponse validateOtp(String email, String otp, String token) {
        User user = userRepository.findByEmail(email).orElse(null);
        Otp otpob = user.getOtp();
        if(user == null)
        {
            return OtpResponse.builder()
                    .OtpToken("").Message("user not found")
                    .build();
        }
        if(otpob.getOtp().equals(otp))
        {
            if (jwtService.isTokenExpired(token))
            {
                return OtpResponse.builder()
                        .OtpToken("").Message("Token expired")
                        .build();
            }
            if(!otpob.getToken().equals(token))
            {
                return OtpResponse.builder()
                        .OtpToken("").Message("Wrong Token")
                        .build();
            }
            if(otpob.getExpiry().compareTo(Instant.now())<0) {
                return OtpResponse.builder()
                        .OtpToken("Otp Expired")
                        .build();
            }
            String newToken = jwtService.generateToken(email);
            otpob.setToken(newToken);
            otpRepository.save(otpob);
            return OtpResponse.builder()
                    .Message("verified")
                    .OtpToken(newToken)
                    .build();
        }
        else {
            return OtpResponse.builder()
                    .OtpToken("").Message("Wrong Otp")
                    .build();

        }
    }

    public ServiceStatus newPassword(String email, String token, String newPassword) {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user.getOtp().getToken().equals(token))
        {
            user.setPassword(newPassword);
            userRepository.save(user);
            return ServiceStatus.Done;
        }
        return ServiceStatus.Wrong_Token;

    }
}



