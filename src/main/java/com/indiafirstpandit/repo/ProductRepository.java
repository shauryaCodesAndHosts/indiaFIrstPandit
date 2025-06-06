package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.Category;
import com.indiafirstpandit.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    // Find products by category
//    List<Product> findByCategoryContainingIgnoreCase(Category category);

    // Find products by stock count
    List<Product> findByStockGreaterThan(int stock);

    // Find products with a price less than or equal to a given value
    List<Product> findByPriceLessThanEqual(Double price);


    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.category.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Product> searchProducts(@Param("searchTerm") String searchTerm);

}
