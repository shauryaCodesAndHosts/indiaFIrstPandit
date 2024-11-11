package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

@RestController
@RequestMapping("/test")
public class DevController {

    @GetMapping
    public String session(HttpServletRequest request)
    {

//        return request.getSession().getId();
        System.out.println(request);
//        return request.getSession().getId();
        return "hello baby";
    }


    @GetMapping("/home")
    public String home(HttpServletRequest request) {

        // Log Request Method
        System.out.println("HTTP Method: " + request.getMethod());

        // Log Request Headers
        System.out.println("Request Headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        // Log Query Parameters (for GET requests)
        System.out.println("Query Parameters:");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println(paramName + ": " + request.getParameter(paramName));
        }

        // Log Session ID (if any)
        if (request.getSession(false) != null) {
            System.out.println("Session ID: " + request.getSession().getId());
        } else {
            System.out.println("No session");
        }

        return "Request logged";
    }

    @GetMapping("/getLoggedInUser")
    public String getLoggedInUser(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        System.out.println("hello");
        System.out.println(userPrincipal.getUser().getAddresses());
        return "hello";
    }

    @GetMapping("/hello")
    public String helloWorld()
    {
        return "hello bitatch";
    }

    @Autowired
    AuthenticationManager manager;


    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        System.out.println("the user is ->"+user);
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPassword()
                )
        );

        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(user.getEmail());
        }
        else
        {
            return "wrong password" ;
        }
    }

}
