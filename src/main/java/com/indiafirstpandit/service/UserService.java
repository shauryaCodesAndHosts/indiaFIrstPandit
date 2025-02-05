package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Cart;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



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
}
