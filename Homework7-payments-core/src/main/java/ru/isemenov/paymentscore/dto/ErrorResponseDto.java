package ru.isemenov.paymentscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @JsonProperty("responseCode")
    private int responseCode;

    @JsonProperty("reason")
    private String reason;
}
