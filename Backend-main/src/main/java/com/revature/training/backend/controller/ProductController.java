package com.revature.training.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.training.backend.entity.Product;
import com.revature.training.backend.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by id using ResponseEntity
    @RequestMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) throws Exception {
        Product FoundProduct = productService.getProductById(id);
            return new ResponseEntity<>(FoundProduct, HttpStatus.OK);
    }

    // Create new product
    @RequestMapping("/products/create")
    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    // Update product
    @RequestMapping("/products/update")
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    // Delete product
    @RequestMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    // Get products by category
    @RequestMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // Search products by name
    @RequestMapping("/products/search/{name}")
    public List<Product> searchProductsByName(@PathVariable String name) {
        return productService.searchProductsByName(name);
    }

    // Get product by price range
    @RequestMapping("/products/price-range/{minPrice}/{maxPrice}")
    public List<Product> getProductsByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return productService.getProductsByPriceRange(minPrice, maxPrice);
    }

    // Get products with low stock
    @RequestMapping("/products/last-chance/{amount}")
    public List<Product> getLowStockProducts(@PathVariable int amount) {
        return productService.getLowStockProducts(amount);
    }

    // Get latest products
    @RequestMapping("/products/newest/{date}")
    public List<Product> getLatestProducts(@PathVariable String date) {
        return productService.getLatestProducts(date);
    }
    
    

    
}
