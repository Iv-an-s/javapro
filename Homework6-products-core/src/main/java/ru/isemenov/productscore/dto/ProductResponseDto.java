package ru.isemenov.productscore.dto;

import java.util.List;

public class ProductResponseDto {
    private List<ProductInfoDto> products;
    private String status;

    public ProductResponseDto(List<ProductInfoDto> products, String status) {
        this.products = products;
        this.status = status;
    }

    public ProductResponseDto() {
    }

    public List<ProductInfoDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfoDto> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductResponseDto{" +
                "products=" + products +
                ", status='" + status + '\'' +
                '}';
    }
}
