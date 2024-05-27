package ru.isemenov.paymentscore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentExecutorRequestDto {

    private Long userId;
    private Long productId;
    private Integer amount;
}
