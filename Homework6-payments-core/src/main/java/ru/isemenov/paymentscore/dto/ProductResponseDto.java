package ru.isemenov.paymentscore.dto;

import java.util.List;

public class ProductResponseDto {
    private List<ProductDto> products;
    private String status;

    public ProductResponseDto() {
    }

    public ProductResponseDto(List<ProductDto> products, String status) {
        this.products = products;
        this.status = status;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
