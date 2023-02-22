package com.discovery.service.exception;

import lombok.Data;

/**
 * Data not found exception class.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@Data
public class UserNotFoundException extends Exception {
    String errorMessage;
    String errorCode;

    public UserNotFoundException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
