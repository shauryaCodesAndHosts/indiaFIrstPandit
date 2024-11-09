package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.PujaCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PujaCategoryRepository extends JpaRepository<PujaCategory, UUID> {

}
