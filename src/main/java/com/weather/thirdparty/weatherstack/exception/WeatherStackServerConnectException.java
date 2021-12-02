package com.weather.thirdparty.weatherstack.exception;

import lombok.Getter;

@Getter
public class WeatherStackServerConnectException extends Exception {
    @Getter
    private String message;

    public WeatherStackServerConnectException(String message) {
        super();
        this.message = message;
    }
}