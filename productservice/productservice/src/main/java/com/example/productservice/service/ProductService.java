package com.example.productservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.productservice.client.InventoryServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.dto.InventoryDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private InventoryServiceClient inventoryserviceclient;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public List<ProductDTO> getAll() {
        return repository.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<ProductDTO> getById(Long id) {
        return repository.findById(id).map(ProductMapper::toDTO);
    }

   public ProductDTO create(ProductDTO dto) {
    log.info("checking availability for product code {} ", dto.getProductCode());
    InventoryDTO inventory = inventoryserviceclient.getInventoryByProductCode(dto.getProductCode());

    try {
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

    public ProductDTO createProductFallback (ProductDTO dto , Throwable t ){
        log.info("Inventory service is down. Fallback executed for product code {}. Error {}.",dto.getProductCode() , t.getMessage());
        throw new RuntimeException("Inventory service is currently available , please try again later");
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