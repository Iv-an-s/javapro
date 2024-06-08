package ru.isemenov.limitcore.dto;

import java.math.BigDecimal;

public record UpdateLimitDto(Long userId, BigDecimal paymentSize) {
}
