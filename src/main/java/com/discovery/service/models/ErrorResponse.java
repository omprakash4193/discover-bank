package com.discovery.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Error response DTO class.
 *
 * @author omprasa2
 * @since 2023-03-21
 */
@Data
public class ErrorResponse {

    @JsonProperty("code")
    private String errorCode;

    @JsonProperty("message")
    private String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
