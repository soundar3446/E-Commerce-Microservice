package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryDTO;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public List<InventoryDTO> getAll() {
        return repository.findAll().stream()
                .map(InventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InventoryDTO> getByProductCode(String productCode) {
        return repository.findByProductCode(productCode)
                .map(InventoryMapper::toDTO);
    }

    public InventoryDTO create(InventoryDTO dto) {
        Inventory inventory = repository.save(InventoryMapper.toEntity(dto));
        return InventoryMapper.toDTO(inventory);
    }

    public Optional<InventoryDTO> update(Long id, InventoryDTO dto) {
        return repository.findById(id)
                .map(inv -> {
                    inv.setQuantity(dto.getQuantity());
                    return InventoryMapper.toDTO(repository.save(inv));
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(inv -> {
            repository.delete(inv);
            return true;
        }).orElse(false);
    }
}