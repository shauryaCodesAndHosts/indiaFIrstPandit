package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.homepage.HomepageSection;
import com.indiafirstpandit.model.homepage.SectionDataItem;
import com.indiafirstpandit.requests.HomepageUpdateRequest;
import com.indiafirstpandit.requests.JustUUID;
import com.indiafirstpandit.response.SearchResults;
import com.indiafirstpandit.service.HomepageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/homepage")
public class HomepageController {

    @Autowired
    private HomepageConfigService homepageConfigService;

    @GetMapping("/getAllActive")
    public ResponseEntity<List<HomepageSection>> getHomepageConfig()
    {
        return ResponseEntity.ok(homepageConfigService.getActiveHomepageConfigSections());
    }


    @PostMapping("/addSection")
    public ResponseEntity<List<HomepageSection>> addSection(@RequestBody HomepageSection homepageSection)
    {
        //add a new homepage section
        return ResponseEntity.ok(homepageConfigService.addHomepageSection(homepageSection));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<HomepageSection> updateHomePageSection(@PathVariable UUID id, @RequestBody HomepageSection homepageSection)
    {
        //update the section information not the section data
        return ResponseEntity.ok(homepageConfigService.updateHomepageSection(id, homepageSection));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHomepageSection(@PathVariable UUID id)
    {
        homepageConfigService.deleteHomepageSection(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllActiveSectionItems/{id}")
    public ResponseEntity<List<SectionDataItem>> getAllActiveSectionItems(@PathVariable UUID id)
    {
        return ResponseEntity.ok(homepageConfigService.getAllActiveSectionItems(id));
    }

    @PostMapping("/addSectionItem/{id}")
    public ResponseEntity<List<SectionDataItem>> addSectionItem(@PathVariable UUID id, @RequestBody SectionDataItem sectionDataItem)
    {
        return ResponseEntity.ok(homepageConfigService.addSectionDataItem(id, sectionDataItem));
    }

    @PutMapping("/updateSectionDataItem/{homepageSectionId}")
    public ResponseEntity<List<SectionDataItem>> updateSectionDataItem(@PathVariable UUID homepageSectionId, @RequestBody SectionDataItem sectionDataItem )
    {
        // section data item must have the id defined in the body
        return ResponseEntity.ok(homepageConfigService.updateHomepageSectionDataItem(homepageSectionId, sectionDataItem));
    }

    @DeleteMapping("/deleteSectionDataItem/{homepageSectionId}")
    public ResponseEntity<List<SectionDataItem>> deleteSectionDataItem(@PathVariable UUID homepageSectionId, @RequestBody JustUUID id)
    {
        // section data item must have the id defined in the body
        return ResponseEntity.ok(homepageConfigService.deleteHomepageSectionDataItem(homepageSectionId, id.getId()));
    }


    @GetMapping("/search/{keyword}")
    public ResponseEntity<SearchResults> search(@PathVariable String keyword)
    {
        SearchResults results = homepageConfigService.searchByKeyword(keyword);
        return ResponseEntity.ok(results);
    }

}
