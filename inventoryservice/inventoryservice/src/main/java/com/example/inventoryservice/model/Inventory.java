package com.example.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private String productCode;

    public Inventory() {}

    public Inventory(Long id, Integer quantity, String productCode) {
        this.id = id;
        this.quantity = quantity;
        this.productCode = productCode;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getProductCode() {return productCode;}
    public void setProductCode(String productCode) {this.productCode = productCode;}
}