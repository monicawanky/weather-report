package com.weather.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;

    public ApiException(String message) {
        super(message);
        this.httpStatus = HttpStatus.OK;
    }
}
