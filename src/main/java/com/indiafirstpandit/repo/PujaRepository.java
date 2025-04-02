package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Puja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


//    @Query("SELECT p FROM Puja p WHERE " +
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
//            "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
//            "LOWER(p.keywords) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")

            @Query("SELECT p FROM Puja p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.benefits) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.mantra) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.keywords) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Puja> searchPujas(@Param("searchTerm") String searchTerm);


}
