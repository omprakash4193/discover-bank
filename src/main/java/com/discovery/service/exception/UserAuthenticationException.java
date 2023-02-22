package com.discovery.service.exception;

import lombok.Data;

/**
 * User auth exception class.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@Data
public class UserAuthenticationException extends Exception {
    String errorMessage;
    String errorCode;

    public UserAuthenticationException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
