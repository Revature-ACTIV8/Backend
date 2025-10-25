package com.revature.training.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.training.backend.entity.Product;
import com.revature.training.backend.repository.ProductRepository;

@Service
public class ProductService {

    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by id throw global exception if not found
    public Product getProductById(int id) throws Exception {
        Product foundProduct = productRepository.findById(id).orElse(null); 
        if (foundProduct == null) {
            throw new Exception("Product not found with id: " + id);
        } else {
            return foundProduct;
        } 
    }

    // Create new product 
    // More logic has to be added here based on which fields are mandatory for prouct creation
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update product
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete product   
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    // Get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Search products by name
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Update stock quantity

    
    // Get products by price range
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Get low stock products   
    public List<Product> getLowStockProducts(int amount) {
        return productRepository.findByStockQuantityLessThan(amount);
    }

    // Get latest products
    public List<Product> getLatestProducts(String date) {
        return productRepository.findByCreatedAtAfter(date);
    }

    
}
