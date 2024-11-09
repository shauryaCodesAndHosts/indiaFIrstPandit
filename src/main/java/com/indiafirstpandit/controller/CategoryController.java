package com.indiafirstpandit.controller;

import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.UserPrincipal;
import com.indiafirstpandit.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    public Category createCategory(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Category category)
    {
//        if(userPrincipal.getUser()) Add the relevent check for user role
        return categoryService.addCategory(category);
    }

    @GetMapping("/getAllProducts/{id}")
    public List<Product> getAllProducts(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable UUID id)
    {
        Category category = categoryService.getCategory(id);
        System.out.println(id);
        System.out.println(category);
        return category.getProduct();
    }

}
