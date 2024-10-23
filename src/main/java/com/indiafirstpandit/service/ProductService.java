package com.indiafirstpandit.service;

import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.repo.CategoryRepository;
import com.indiafirstpandit.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProductsByCategory(Category category) {
//        return productRepository.findByCategoryContainingIgnoreCase(category);
//        return category.getProduct();
//        return null;
        return categoryRepository.getReferenceById(category.getId()).getProduct();
    }

    public List<Product> getProductsInStock() {
        return productRepository.findByStockGreaterThan(0);
    }

    public List<Product> getProductsByPriceLessThan(Double price) {
        return productRepository.findByPriceLessThanEqual(price);
    }

    public Product saveProduct(Product product) {
        System.out.println(product);
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
