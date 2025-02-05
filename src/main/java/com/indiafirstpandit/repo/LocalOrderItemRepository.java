package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.LocalOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalOrderItemRepository extends JpaRepository<LocalOrderItem, UUID> {
}
