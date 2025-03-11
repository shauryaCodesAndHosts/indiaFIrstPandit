package com.indiafirstpandit.repo.homepage;

import com.indiafirstpandit.model.homepage.SectionDataItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SectionDataItemRepo extends JpaRepository<SectionDataItem, UUID> {

    List<SectionDataItem> findByIsActiveTrueOrderByDisplayOrderAsc();
}
