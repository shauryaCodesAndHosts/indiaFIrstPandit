package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.LocalOrder;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
    public interface LocalOrderRepository extends JpaRepository<LocalOrder, UUID> {

        // Find all orders by a specific user
        List<LocalOrder> findByUserId(UUID userId);
//
//        // Find all orders associated with a specific Puja
//        List<LocalOrder> findByPujaId(UUID pujaId);
//
//        // Find all orders associated with a specific Samagri
//        List<LocalOrder> findBySamagriId(UUID samagriId);
    }
