package com.indiafirstpandit.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initFirebase() {
        try {
            // this method was not suitable or production because the path is hardcoded here
//            FileInputStream serviceAccount =
//                    new FileInputStream("src/main/resources/indiafirstpandit-firebase-adminsdk-fbsvc-584c89b303.json");

            InputStream serviceAccount = getClass().getClassLoader()
                    .getResourceAsStream("indiafirstpandit-firebase-adminsdk-fbsvc-584c89b303.json");

            if (serviceAccount == null) {
                throw new IllegalStateException("Firebase service account JSON not found in classpath.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("indiafirstpandit.firebasestorage.app") // Replace with your Firebase bucket
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
