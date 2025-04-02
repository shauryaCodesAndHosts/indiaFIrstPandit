package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Samagri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SamagriRepository extends JpaRepository<Samagri, UUID> {

    // Find samagri items by name (partial match, case-insensitive)
    List<Samagri> findByNameContainingIgnoreCase(String name);

    // Find all samagris that contain a specific product
    List<Samagri> findByProductsId(UUID productId);

    @Query("SELECT s FROM Samagri s WHERE " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "EXISTS (SELECT p FROM s.products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR " +
            "EXISTS (SELECT p FROM s.pujas p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Samagri> searchSamagris(@Param("searchTerm") String searchTerm);

}
