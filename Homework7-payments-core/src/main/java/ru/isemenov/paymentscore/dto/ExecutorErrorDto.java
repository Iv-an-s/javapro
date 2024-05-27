package ru.isemenov.paymentscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExecutorErrorDto (
        @JsonProperty("responseCode") Integer responseCode,
        @JsonProperty("reason") String reason){}