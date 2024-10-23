package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
    public interface OrderRepository extends JpaRepository<Order, UUID> {

        // Find all orders by a specific user
        List<Order> findByUserId(UUID userId);

        // Find all orders associated with a specific Puja
        List<Order> findByPujaId(UUID pujaId);

        // Find all orders associated with a specific Samagri
        List<Order> findBySamagriId(UUID samagriId);
    }
