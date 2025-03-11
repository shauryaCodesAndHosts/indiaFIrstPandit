package com.indiafirstpandit.repo.homepage;

import com.indiafirstpandit.model.homepage.HomepageSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HomepageConfigRepo extends JpaRepository<HomepageSection, UUID> {
    List<HomepageSection> findByIsActiveTrueOrderByDisplayOrderAsc();
}


