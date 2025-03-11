package com.indiafirstpandit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indiafirstpandit.model.homepage.HomepageSection;
import com.indiafirstpandit.model.homepage.SectionDataItem;
import com.indiafirstpandit.repo.homepage.HomepageConfigRepo;
import com.indiafirstpandit.repo.homepage.SectionDataItemRepo;
import com.indiafirstpandit.requests.JustUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class HomepageConfigService {

    @Autowired
    private HomepageConfigRepo homepageConfigRepo;

    @Autowired
    private SectionDataItemRepo sectionDataItemRepo;

    private ObjectMapper objectMapper;

    public List<HomepageSection> getActiveHomepageConfigSections()
    {
        return homepageConfigRepo.findByIsActiveTrueOrderByDisplayOrderAsc();
    }


    public List<HomepageSection> addHomepageSection(HomepageSection homepageSection)
    {
        homepageConfigRepo.save(homepageSection);
        return homepageConfigRepo.findAll();
    }

    public HomepageSection updateHomepageSection(UUID homepageSectionId,HomepageSection newHomepageSection )
    {
        HomepageSection homepageSection = homepageConfigRepo.findById(homepageSectionId).orElseThrow();
        homepageSection.setHomepageSectionType(newHomepageSection.getHomepageSectionType());
        homepageSection.setSectionName(newHomepageSection.getSectionName());
        homepageSection.setDisplayOrder(newHomepageSection.getDisplayOrder());
        homepageSection.setIsActive(newHomepageSection.getIsActive());
        return homepageConfigRepo.save(homepageSection);
    }

    public void deleteHomepageSection(UUID id) {
        homepageConfigRepo.deleteById(id);
        return;
    }



    public List<SectionDataItem> getAllActiveSectionItems(UUID uuid) {
        HomepageSection homepageSection = homepageConfigRepo.findById(uuid).orElseThrow();
        return homepageSection.getSectionDataItems();
    }

    public List<SectionDataItem> addSectionDataItem(UUID homepageSectionId, SectionDataItem sectionDataItem)
    {
        HomepageSection homepageSection = homepageConfigRepo.findById(homepageSectionId).orElseThrow();
        SectionDataItem sectionDataItem1 = sectionDataItemRepo.save(sectionDataItem);
        homepageSection.getSectionDataItems().add(sectionDataItem1);
        homepageConfigRepo.save(homepageSection);
        return homepageSection.getSectionDataItems();
    }

    public List<SectionDataItem> updateHomepageSectionDataItem(UUID homepageSectionId, SectionDataItem newSectionDataItem) {
//        return null;
        HomepageSection homepageSection = homepageConfigRepo.findById(homepageSectionId).orElseThrow();
        SectionDataItem sectionDataItem = sectionDataItemRepo.findById(newSectionDataItem.getId()).orElseThrow();
        homepageSection.getSectionDataItems().remove(sectionDataItem);
        sectionDataItemRepo.deleteById(newSectionDataItem.getId());
        System.out.println(homepageSection.getSectionDataItems());

        SectionDataItem sectionDataItem1 = new SectionDataItem();
        sectionDataItem1.setItemId(newSectionDataItem.getItemId());
        sectionDataItem1.setName(newSectionDataItem.getName());
        sectionDataItem1.setDisplayOrder(newSectionDataItem.getDisplayOrder());
        sectionDataItem1.setIsActive(newSectionDataItem.getIsActive());
        sectionDataItem1.setImage(newSectionDataItem.getImage());
        sectionDataItemRepo.save(sectionDataItem1);

        homepageSection.getSectionDataItems().add(sectionDataItem1);
        homepageSection.setSectionDataItems(homepageSection.getSectionDataItems());
        homepageConfigRepo.save(homepageSection);
//        homepageSection.setSectionDataItems();
        return homepageSection.getSectionDataItems();
    }

    public List<SectionDataItem> deleteHomepageSectionDataItem(UUID homepageSectionId, UUID id) {
        SectionDataItem sectionDataItem = sectionDataItemRepo.findById(id).orElseThrow();
        HomepageSection homepageSection = homepageConfigRepo.findById(homepageSectionId).orElseThrow();
        homepageSection.getSectionDataItems().remove(sectionDataItem);
        sectionDataItemRepo.deleteById(id);
        homepageConfigRepo.save(homepageSection);
        return homepageSection.getSectionDataItems();
    }

//
//    public HomepageSection updateHomepageSection(UUID id, String sectionData)
//    {
//        HomepageSection config = homepageConfigRepo.findById(id).get();
////        config.setSectionData(sectionData);
//        config.setLastUpdated(LocalDateTime.now());
//        homepageConfigRepo.save(config);
//        return config;
//    }




}
