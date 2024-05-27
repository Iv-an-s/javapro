package ru.isemenov.productscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductInfoDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("category")
    private String categoryTitle;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("available_count")
    private Integer quantity;
}
