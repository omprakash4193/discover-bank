package com.discovery.service.exception;


import com.discovery.service.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler class.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericBusinessException(GenericException ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getErrorMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundBusinessException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = UserAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedBusinessException(UserAuthenticationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
