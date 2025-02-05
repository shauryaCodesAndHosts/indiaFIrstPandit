package com.indiafirstpandit.dto;

import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.repo.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Autowired
    private CategoryRepository categoryRepository;

    private UUID id;
    private String name;
    private String image;
    private String description;
    private Double price;
    private Integer stock;
    private UUID categoryId;
    private LocalDateTime createdAt;


    public ProductDto(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.image = product.getImage();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.categoryId = product.getCategory().getId();
        this.createdAt = product.getCreatedAt();
    }

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getName());
        product.setPrice(this.getPrice());
        product.setStock(this.getStock());
        product.setImage(this.getImage());
        product.setDescription(this.getDescription());
        product.setCreatedAt(this.getCreatedAt());
        product.setCategory(categoryRepository.findById(this.getCategoryId()).orElse(new Category()));

        return product;
    }


}
