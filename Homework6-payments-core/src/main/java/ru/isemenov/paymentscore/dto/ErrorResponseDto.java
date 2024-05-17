package ru.isemenov.paymentscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDto{
    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("reason")
    private String reason;

    public ErrorResponseDto(int responseCode, String reason) {
        this.responseCode = responseCode;
        this.reason = reason;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
