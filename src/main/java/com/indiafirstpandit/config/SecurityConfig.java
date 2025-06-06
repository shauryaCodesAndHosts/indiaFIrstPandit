package com.indiafirstpandit.config;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
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
                    .cors(Customizer.withDefaults()) // Enable CORS
                    .authorizeHttpRequests(request -> request
//                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()  // Allow POST requests to /api/users
//                            .requestMatchers(HttpMethod.GET, "/api/users/email").permitAll()
                            .requestMatchers("/api/users","/api/users/email**", "/api/users/**").permitAll()   // Allow all other access to /api/users and subpaths
//                            .requestMatchers("/swagger-ui/**","/swagger-ui**","/swagger-ui").permitAll()
//                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasAnyAuthority("ROLE_ADMIN")  // Allow access to Swagger UI and OpenAPI docs
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/test/login","register").permitAll()
                            .requestMatchers("/test/testTime").permitAll()
                            .requestMatchers("/api/v1/auth/register").permitAll()
                            .requestMatchers("/api/v1/auth/verify").permitAll()
                            .requestMatchers("/api/v1/auth/login").permitAll()
                            .requestMatchers("/api/v1/auth/resendOtp").permitAll()
                            .requestMatchers("/api/v1/auth/refresh").permitAll()
                            .requestMatchers("/api/v1/auth/forgotPassword/email").permitAll()
                            .requestMatchers("/api/v1/auth/forgotPassword/validateOtp").permitAll()
                            .requestMatchers("/api/v1/auth/forgotPassword/newPassword").permitAll()


                            .anyRequest().authenticated());                                // All other requests need authentication
//                    .oauth2Login(Customizer.withDefaults()) ;
//                    .httpBasic(Customizer.withDefaults())                             // Use Basic authentication
//                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless sessions
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
;
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

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Allow all origins. Use specific origins in production for security.
        config.addAllowedHeader("*"); // Allow all headers.
        config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.).
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
