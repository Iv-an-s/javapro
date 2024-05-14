package ru.isemenov.productscore.model;

public class Product {
    private Long id;
    private ProductType productType;
    private Integer price;

    public Product(Long id, ProductType productType, Integer price) {
        this.id = id;
        this.productType = productType;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType=" + productType +
                ", price=" + price +
                '}';
    }
}
