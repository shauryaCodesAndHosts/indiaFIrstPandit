package com.indiafirstpandit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }


    @Bean
    public SecurityFilterChain security (HttpSecurity http) throws Exception {
//        http.csrf(customizer -> customizer.disable());
//        http.authorizeHttpRequests(request -> request.requestMatchers("/api/users*").permitAll().anyRequest().authenticated());
//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();

            http.csrf(customizer -> customizer.disable())
                    .authorizeHttpRequests(request -> request
                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()  // Allow POST requests to /api/users
                            .requestMatchers(HttpMethod.GET, "/api/users/email").permitAll()
                            .requestMatchers("/api/users","/api/users/email**", "/api/users/**").permitAll()   // Allow all other access to /api/users and subpaths
                            .anyRequest().authenticated())                                // All other requests need authentication
                    .httpBasic(Customizer.withDefaults())                             // Use Basic authentication
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless sessions

            return http.build();

    }

//   @Bean
//    public UserDetailsService userDetailsService()
//   {
//       UserDetails user = User.withDefaultPasswordEncoder().username("saksham")
//               .password("12345")
//               .roles("USER")
//               .build();
//       return new InMemoryUserDetailsManager(user);
//   }


}
