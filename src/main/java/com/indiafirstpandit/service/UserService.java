package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.mailDto.SimpleMail;
import com.indiafirstpandit.enums.UserRoles;
import com.indiafirstpandit.enums.VerificationStatus;
import com.indiafirstpandit.model.*;
import com.indiafirstpandit.repo.CartRepository;
import com.indiafirstpandit.repo.OtpRepository;
import com.indiafirstpandit.repo.UserRepository;
import com.indiafirstpandit.repo.UsersDeletedRepository;
import com.indiafirstpandit.requests.LoginRequest;
import com.indiafirstpandit.requests.RegisterRequest;
import com.indiafirstpandit.response.JwtResponse;
import com.indiafirstpandit.response.RegisterResponse;
import com.indiafirstpandit.response.UserProfileResponse;
import com.indiafirstpandit.service.auth.RefreshTokenService;
import com.indiafirstpandit.service.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UsersDeletedRepository usersDeletedRepository;

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public List<User> getUsersInGeographicRegion(double minLat, double maxLat, double minLng, double maxLng) {
        return userRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLng, maxLng);
    }

    public User saveUser(User user) {
        if (user.getCart() == null) {
            Cart cart = new Cart();
            user.setCart(cart);
        }
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public RegisterResponse register(RegisterRequest registerRequest) {

        if(userRepository.existsByEmail(registerRequest.getEmail()))
        {
            return RegisterResponse.builder().status(VerificationStatus.already_registered).build();
        }

        User existingUser = userRepository.findByEmail(registerRequest.getEmail()).orElse(new User());
        if (existingUser.getEmail() != null && !existingUser.isVerified() )
        {
            return RegisterResponse.builder().email("exists").build(); //to check in the controller
        }
        Cart cart = new Cart();
        cartRepository.save(cart);

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .name(registerRequest.getName())
                .cart(cart)
                .role(UserRoles.Customer)
                .build();

        System.out.println( "received user -> " + user);
        System.out.println("register request -> " + registerRequest);
        String otp = generateOTP();
        Otp otpOb = Otp.builder()
                .otp(otp)
                .expiry(Instant.now().plusMillis(1000*60*5))
                .build();
        otpRepository.save(otpOb);
        user.setOtp(otpOb);

        User savedUser = userRepository.save(user);

        System.out.println("saved user -> " + savedUser);
        sendVerificationMail(savedUser.getEmail(), otp);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .build();
    }

    public RegisterResponse verify(String email, String otp)
    {
        User user = userRepository.findByEmail(email).orElse(new User());

        if(user.getOtp().getExpiry().compareTo(Instant.now())<0)
        {
            return RegisterResponse.builder().status(VerificationStatus.expired_otp).build();
        }


            if ( user.getEmail() != null )
        {
            if(user.isVerified())
            {
                return RegisterResponse.builder().status(VerificationStatus.already_verified).build();
            }
            else {
                if(otp.equals(user.getOtp().getOtp()))
                {
                    user.setVerified(true);
                    userRepository.save(user);
                    LoginRequest request = LoginRequest.builder()
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .build();
                    JwtResponse response = login(request).getBody();
                    return RegisterResponse.builder()
                            .status(VerificationStatus.verified)
                            .jwtToken(response.getJwtToken())
                            .refreshToken(response.getRefreshToken())
                            .expiry(response.getExpiry())
                            .email(request.getEmail())
                            .build();
                }
                else {
                    return RegisterResponse.builder().status(VerificationStatus.wrong_otp).build();
                }
            }
        }
        else {
                return RegisterResponse.builder().status(VerificationStatus.user_not_found).build();
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

    public ResponseEntity<JwtResponse> login(LoginRequest loginRequest)
    {
//        System.out.println("the user is ->"+user);
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );
        User existingUser = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(JwtResponse.builder().message("User not found").build());
        }
        if(existingUser.isVerified()) {
            if (authentication.isAuthenticated()) {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());
                return ResponseEntity.ok(JwtResponse.builder()
                        .jwtToken(jwtService.generateToken(loginRequest.getEmail()))
                        .refreshToken(refreshToken.getRefreshToken())
                        .expiry(refreshToken.getExpiry())
                        .userName(loginRequest.getEmail())
                                .message("User logged in")
                        .build()
                );
//                return ;
            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(JwtResponse.builder().message("Wrong password").build());
            }
        }
        else {
//            return "get verification";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(JwtResponse.builder().message("please get verification")
                            .build());
    }
    }


    public Object resendOtp(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user != null)
        {
            System.out.println(user);
            if(user.isVerified())
            {
                return RegisterResponse.builder()
                        .email(VerificationStatus.already_verified.toString())
                        .build();
            }

            String otp = generateOTP();
            Otp otpOb = Otp.builder().otp(otp)
                    .expiry(Instant.now().plusMillis(1000*60*5))
                    .build();
            otpRepository.save(otpOb);
            user.setOtp(otpOb);
            userRepository.save(user);
            sendVerificationMail(email, otp);
            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .build();

        }
        return RegisterResponse.builder()
                .email("User Not found")
                .build();

    }

    public UserProfileResponse getUserProfile(User user) {
        return new UserProfileResponse(user);
    }


    public void deleteUser(User user, String reason){

        UsersDeleted usersDeleted = new UsersDeleted();
        usersDeleted.setName(user.getName());
        usersDeleted.setReason(reason);
        usersDeleted.setEmailAddress(user.getEmail());
        usersDeleted.setPhoneNumber(user.getPhoneNumber());
        usersDeleted.setOrdersPlaced(user.getOrders().size());
        userRepository.deleteById(user.getId());
        usersDeletedRepository.save(usersDeleted);
        return;
    }

}





























