package com.example.productservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.dto.InventoryDTO;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<ProductDTO> getAll() {
        return repository.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<ProductDTO> getById(Long id) {
        return repository.findById(id).map(ProductMapper::toDTO);
    }

   public ProductDTO create(ProductDTO dto) {
    String url = "http://localhost:8080/api/inventory/product/" + dto.getProductCode();

    try {
        InventoryDTO inventory = restTemplate.getForObject(url, InventoryDTO.class);

        if (inventory != null && inventory.getQuantity() > 0) {
            Product product = repository.save(ProductMapper.toEntity(dto));
            return ProductMapper.toDTO(product);
        } else {
            throw new RuntimeException("Product not in stock with ID: " + dto.getProductCode());
        }

    } catch (HttpClientErrorException.NotFound e) {
        throw new RuntimeException("Inventory record not found for product ID: " + dto.getProductCode());
    } catch (RuntimeException e) {
        throw new RuntimeException("Inventory check failed: " + e.getMessage());
    }
}


    public Optional<ProductDTO> update(Long id, ProductDTO dto) {
        return repository.findById(id).map(p -> {
            p.setName(dto.getName());
            p.setPrice(dto.getPrice());
            return ProductMapper.toDTO(repository.save(p));
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(p -> {
            repository.delete(p);
            return true;
        }).orElse(false);
    }
}