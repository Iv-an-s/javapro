package ru.isemenov.productscore.dto;

public record OrderDetailsRequestDto (Long userId, Long productId, Integer amount){}
