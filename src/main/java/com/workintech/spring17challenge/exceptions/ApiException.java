package com.workintech.spring17challenge.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends RuntimeException { //📍hata hataları yakalayamabilmek için
    private HttpStatus httpStatus;

    public ApiException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus=httpStatus;
    }
}
