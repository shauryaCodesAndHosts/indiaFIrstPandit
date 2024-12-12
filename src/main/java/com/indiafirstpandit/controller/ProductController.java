package com.indiafirstpandit.controller;

import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.enums.ServiceStatus;
import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/category")
    public List<Product> getProductsByCategory(@RequestBody Category category) {
        System.out.println(category);
        return productService.getProductsByCategory(category);
//        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product savedProduct = productService.saveProduct(productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // sends status code 204
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDto updatedProductDto)
    {
        ServiceStatus status = productService.updateProduct(id, updatedProductDto);
        if(status == ServiceStatus.Done)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}
