package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.PujaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PujaCategoryRepository extends JpaRepository<PujaCategory, UUID> {



}
