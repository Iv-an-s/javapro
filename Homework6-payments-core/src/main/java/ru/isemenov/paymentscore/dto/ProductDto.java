package ru.isemenov.paymentscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("available_count")
    private Integer availableCount;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", availableCount=" + availableCount +
                '}';
    }
}
