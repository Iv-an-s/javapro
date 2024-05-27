package ru.isemenov.paymentscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutorErrorDto {

    @JsonProperty("responseCode")
    private int responseCode;

    @JsonProperty("reason")
    private String reason;
}
