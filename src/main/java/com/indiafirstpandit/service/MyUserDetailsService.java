package com.indiafirstpandit.service;

import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(username);
//        User user = repo.findByEmail(username).orElse(new User());
//        System.out.println(user);
//        return new UserPrincipal(user);

        System.out.println("Username entered: " + username);

        User user = repo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + username));

//        System.out.println("User found: " + user);

        return new UserPrincipal(user);

    }
}
