    package com.example.inventoryservice.dto;

    public class InventoryDTO {
        private Long id;
        private Long productId;
        private Integer quantity;

        public InventoryDTO() {}

        public InventoryDTO(Long id, Long productId, Integer quantity) {
            this.id = id;
            this.productId = productId;
            this.quantity = quantity;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }