    package com.example.inventoryservice.mapper;

    import com.example.inventoryservice.dto.InventoryDTO;
    import com.example.inventoryservice.model.Inventory;

    public class InventoryMapper {
        public static InventoryDTO toDTO(Inventory inv) {
            return new InventoryDTO(inv.getId(),  inv.getQuantity(), inv.getProductCode());
        }

        public static Inventory toEntity(InventoryDTO dto) {
            return new Inventory(dto.getId(),  dto.getQuantity(), dto.getProductCode());
        }
    }