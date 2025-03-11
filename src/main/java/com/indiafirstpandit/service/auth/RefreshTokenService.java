package com.indiafirstpandit.service.auth;

import com.indiafirstpandit.model.RefreshToken;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.repo.RefreshTokenRepository;
import com.indiafirstpandit.repo.UserRepository;
import com.indiafirstpandit.response.JwtResponse;
import com.indiafirstpandit.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    public long refreshTokenValidity = 30 * 24 * 60 * 60 * 1000L ;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public RefreshToken createRefreshToken(String userName)
    {
        User user = userRepository.findByEmail(userName).orElse(null);
        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null)
        {
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
        }
        else {
            refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }
        user.setRefreshToken(refreshToken);
        refreshTokenRepository.save(refreshToken);
        userRepository.save(user);
        return refreshToken;
    }

    public boolean verifyRefreshToken(String refreshToken)
    {
        RefreshToken existingRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new RuntimeException("token does not exist")  );
        if(existingRefreshToken.getExpiry().compareTo(Instant.now())<0)
        {
//            System.out.println(existingRefreshToken);
            refreshTokenRepository.delete(existingRefreshToken);
            //throw error or create
            return false;
        }
        return true;
    }

    public JwtResponse refreshJwtToken(String refreshToken) {
        RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElse(null);
        if( refreshTokenOb != null)
        {
            User user = refreshTokenOb.getUser();
            String jwtToken = jwtService.generateToken(user.getEmail());
            return JwtResponse.builder()
                    .jwtToken(jwtToken)
                    .userName(user.getEmail())
                    .expiry(user.getRefreshToken().getExpiry())
                    .refreshToken(user.getRefreshToken().getRefreshToken())
                    .message("New JWT Assigned")
                    .build();
        }
        return null;
    }
}


































