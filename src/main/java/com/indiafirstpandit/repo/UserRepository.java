package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Find a user by email
    Optional<User> findByEmail(String email);



    // Find a user by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Find all users within a specific geographic region (latitude and longitude)
    List<User> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng);
}
