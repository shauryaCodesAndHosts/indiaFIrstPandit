package com.indiafirstpandit.model.homepage;

import com.indiafirstpandit.enums.HomepageSectionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
//@Table(name = "homepage_config")
public class HomepageSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private HomepageSectionType homepageSectionType;

    @Column
    private String sectionName;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "jsonb")
    @OneToMany
    private List<SectionDataItem> sectionDataItems;

    private Integer displayOrder;

    private Boolean isActive;

    private LocalDateTime lastUpdated;

    @PrePersist
    protected void onCreate()
    {
        this.lastUpdated = LocalDateTime.now();
    }


}
