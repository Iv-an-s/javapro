package ru.isemenov.productscore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductResponseDto {

    private List<ProductInfoDto> products;

    private String status;
}
