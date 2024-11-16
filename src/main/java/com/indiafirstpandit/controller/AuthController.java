//package com.indiafirstpandit.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @PostMapping("/google")
//    public ResponseEntity<String> verifyGoogleToken(@RequestParam String idTokenString) {
//        try {
//
//            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
//                    new NetHttpTransport(), new JacksonFactory())
//                    .setAudience(Collections.singletonList("YOUR_GOOGLE_CLIENT_ID"))
//                    .build();
//
//            GoogleIdToken idToken = verifier.verify(idTokenString);
//            if (idToken != null) {
//                GoogleIdToken.Payload payload = idToken.getPayload();
//
//                String email = payload.getEmail();
//                String name = (String) payload.get("name");
//
//                // Create or fetch user from the database based on the email
//
//                return ResponseEntity.ok("User verified successfully");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying ID token");
//        }
//    }
//}
