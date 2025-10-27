package com.revature.training.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.training.backend.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // Get products by category
    List<Product> findByCategory(String category);

    // Search products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Get products with low stock
    List<Product> findByStockQuantityLessThan(int amount);


    // Get products created after a certain date
    List<Product> findByCreatedAtAfter(String date);

    // Get Products within a price range
    List<Product> findByPriceBetween(double minPrice, double maxPrice);




}
