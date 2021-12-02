package com.weather.exception;

import com.weather.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiResponse> handleApiException(ApiException e, WebRequest request) {

        return ResponseEntity.status(e.getHttpStatus()).body(new ApiResponse(e.getMessage()));
    }
}