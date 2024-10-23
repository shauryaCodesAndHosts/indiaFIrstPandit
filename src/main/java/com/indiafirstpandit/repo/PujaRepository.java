package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Puja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PujaRepository extends JpaRepository<Puja, UUID> {

    // Find pujas by name (partial match, case-insensitive)
    List<Puja> findByNameContainingIgnoreCase(String name);

    // Find pujas by pandit count needed
    List<Puja> findByPanditNeeded(int panditNeeded);

    // Find pujas with a price less than or equal to a specific amount
    List<Puja> findByAmountLessThanEqual(Double amount);
}
