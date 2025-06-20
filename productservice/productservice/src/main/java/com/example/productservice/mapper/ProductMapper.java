package com.example.productservice.mapper;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.model.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    public static Product toEntity(ProductDTO dto) {
        return new Product(dto.getId(), dto.getName(), dto.getPrice());
    }
}
