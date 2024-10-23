package com.indiafirstpandit.service;

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
}
