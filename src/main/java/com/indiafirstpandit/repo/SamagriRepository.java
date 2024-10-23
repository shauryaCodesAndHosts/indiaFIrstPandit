package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Samagri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SamagriRepository extends JpaRepository<Samagri, UUID> {

    // Find samagri items by name (partial match, case-insensitive)
    List<Samagri> findByNameContainingIgnoreCase(String name);

    // Find all samagris that contain a specific product
    List<Samagri> findByProductsId(UUID productId);
}
