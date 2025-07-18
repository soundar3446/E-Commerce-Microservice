package com.example.productservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;
    
    private String productCode; 

    public Product() {}

    public Product(Long id, String name, BigDecimal price, String productCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productCode = productCode;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getProductCode() {return productCode;}
    public void setProductCode() {this.productCode = productCode;}
}