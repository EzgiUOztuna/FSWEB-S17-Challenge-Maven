package com.workintech.spring17challenge.exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiErrorResponse { //📍hata mesajlarını dönmek için
    private Integer status;
    private String message;
    private Long timestamp;
}
