package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Priest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PriestRepository extends JpaRepository<Priest, UUID> {

    // Find priests by expertise
    List<Priest> findByExpertiseContainingIgnoreCase(String expertise);

    // Find priests by partial name match
    List<Priest> findByNameContainingIgnoreCase(String name);

    // Find a priest by contact number
    Priest findByContactNumber(String contactNumber);
}
