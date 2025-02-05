package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.enums.ServiceStatus;
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

    public ProductDto getProductById(UUID id) {
        Product product = productRepository.getReferenceById(id);
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public Product getProductByIdNotProductDto(UUID id) {
        Product product = productRepository.getReferenceById(id);
        return product;
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

    public Product saveProduct(ProductDto product) {
        System.out.println(product);
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setImage(product.getImage());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        newProduct.setCategory(categoryRepository.getReferenceById(product.getCategoryId()));
        productRepository.save(newProduct);
        return new Product();
    }

    public void deleteProduct(UUID id) {

        productRepository.deleteById(id);
    }

    public ServiceStatus updateProduct(UUID id, ProductDto updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(new Product());
        if(existingProduct.getId() == null){
            return ServiceStatus.Not_Found;
        }
        else{
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
            productRepository.save(existingProduct);
            return ServiceStatus.Done;
        }


    }
}
