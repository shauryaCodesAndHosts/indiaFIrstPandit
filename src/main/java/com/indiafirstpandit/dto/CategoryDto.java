package com.indiafirstpandit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CategoryDto {

    public CategoryDto(Category category)
    {
        this.id = category.getId();
        this.createdAt = category.getCreatedAt();
        this.name = category.getName();
        this.description = category.getDescription();
        this.totalItems = category.getTotalItems();

        this.image = category.getImage();

        if(category.getProduct() != null)
        {
            this.product = category.getProduct().stream().map(ProductDto::new).toList();
        }
    }

    private UUID id;
    private String name;
    private String image;
    private String description;
    private Integer totalItems;
    private LocalDateTime createdAt;
    private List<ProductDto> product;

//    public Category CategoryDtoToCategory()
//    {
//        Category category = new Category();
//        category.setId(this.getId());
//        category.setName(this.getName());
//        category.setProduct(List.copyOf(this.getProduct()));
//        category.setImage(this.getImage());
//        category.setDescription(this.getDescription());
//        category.setTotalItems(this.getTotalItems());
//        category.setCreatedAt(this.getCreatedAt());
//
//        return  category;
//    }

}
