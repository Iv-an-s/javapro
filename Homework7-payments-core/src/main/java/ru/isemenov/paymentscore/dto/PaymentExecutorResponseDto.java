package ru.isemenov.paymentscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentExecutorResponseDto{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private String status;
}
