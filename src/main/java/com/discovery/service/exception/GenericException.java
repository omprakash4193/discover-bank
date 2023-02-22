package com.discovery.service.exception;

import lombok.Data;

/**
 * Generic exception class.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@Data
public class GenericException extends Exception {
    private String errorMessage;
    private String errorCode;

    public GenericException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public GenericException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
