package com.workintech.spring17challenge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {  //📍tüm hataları yönetmek için

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(ApiException exception){
        ApiErrorResponse apiResponseError = new ApiErrorResponse (exception.getHttpStatus().value(), exception.getMessage(), System.currentTimeMillis());
        log.error("Exception occurred= ", exception);
        return new ResponseEntity<>(apiResponseError, exception.getHttpStatus());
    }
}
