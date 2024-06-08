package ru.isemenov.limitcore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LimitUpdateResponseDto {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("quota")
    private BigDecimal quota;

    @JsonProperty("status")
    private String status;
}
