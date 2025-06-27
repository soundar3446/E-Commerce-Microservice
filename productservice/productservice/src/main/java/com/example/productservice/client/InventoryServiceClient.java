package com.example.productservice.client;

import com.example.productservice.dto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @GetMapping("/api/inventory/{productCode}")
    InventoryDTO getInventoryByProductCode(@PathVariable("productCode") String productCode);
}