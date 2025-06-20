    package com.example.productservice.dto;

    public class InventoryDTO {
        private Long id;
        private Integer quantity;
        private String productCode;

        public InventoryDTO() {}

        public InventoryDTO(Long id,Integer quantity, String productCode) {
            this.id = id;
            this.productCode = productCode;
            this.quantity = quantity;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }


        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }

        public String getProductCode(){ return productCode; }
        public void setProductCode(String productCode){this.productCode = productCode;}
    }