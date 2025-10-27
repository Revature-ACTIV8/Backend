package com.revature.training.backend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Product {


    @Id
    private int id;
    private String name;
    private double price;
    private String description;
    private String category;
    private String imageUrl;
    private int stockQuantity;
    private String createdAt;

    
}
