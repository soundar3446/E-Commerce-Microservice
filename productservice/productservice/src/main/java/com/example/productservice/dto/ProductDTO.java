package com.example.productservice.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;;
    private String productCode;
    

    public ProductDTO() {}

    public ProductDTO(Long id, String name, BigDecimal price, String productCode) {
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

    public String getProductCode () {return productCode;}
    public void setProductCode () { this.productCode = productCode;}


}
