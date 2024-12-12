package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.CategoryDto;
import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category)
    {
        categoryRepository.save(category);
        return category;
    }

    public Category getCategory(UUID id) {
        return categoryRepository.getReferenceById(id);
    }

    public boolean deleteCatgory(UUID id) {
        try
        {
            categoryRepository.deleteById(id);
            return true;
        }
        catch (Error e)
        {
            return false;
        }
    }

    public ServiceStatus updateCategory(UUID id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id).orElse(new Category());
        CategoryDto categoryDto = new CategoryDto(updatedCategory);
        if(existingCategory.getId()==null)
        {
            return ServiceStatus.Not_Found;
        }
        else
        {
            if(categoryDto.getName()!=null)
            {
                existingCategory.setName(categoryDto.getName());
            }
            if (categoryDto.getDescription()!=null)
            {
                existingCategory.setDescription(categoryDto.getDescription());
            }
            if (categoryDto.getTotalItems()!=null)
            {
                existingCategory.setTotalItems(categoryDto.getTotalItems());
            }
            if(categoryDto.getProduct()!=null)
            {
                existingCategory.setProduct(categoryDto.getProduct().stream().map(ProductDto::toProduct).toList());
            }
            if(categoryDto.getImage()!=null)
            {
                existingCategory.setImage(categoryDto.getImage());
            }
            if (categoryDto.getCreatedAt()!=null)
            {
                existingCategory.setCreatedAt(categoryDto.getCreatedAt());
            }
            categoryRepository.save(existingCategory);
            return ServiceStatus.Done;
        }
    }
}
