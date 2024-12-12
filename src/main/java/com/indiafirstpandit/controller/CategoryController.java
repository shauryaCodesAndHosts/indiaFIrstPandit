package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.CategoryDto;
import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAll()
    {
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category)
    {
//        if(userPrincipal.getUser()) Add the relevent check for user role
        return categoryService.addCategory(category);
    }

    @GetMapping("/getAllProducts/{id}")
    public List<ProductDto> getAllProducts(@PathVariable UUID id)
    {
        Category category = categoryService.getCategory(id);
        System.out.println(id);
        System.out.println(category);
        CategoryDto categoryDto = new CategoryDto(category);
        return categoryDto.getProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable UUID id)
    {
        System.out.println("hello i am category");
        Category category = categoryService.getCategory(id);
        CategoryDto categoryDto = new CategoryDto(category);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id)
    {
        categoryService.deleteCatgory(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> updateProduct(@PathVariable UUID id, @RequestBody Category updatedCategory)
    {
        ServiceStatus status = categoryService.updateCategory(id, updatedCategory);
        if(status == ServiceStatus.Done)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

}
