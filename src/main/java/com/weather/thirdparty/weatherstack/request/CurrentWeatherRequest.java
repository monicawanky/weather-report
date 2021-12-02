package com.weather.thirdparty.weatherstack.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CurrentWeatherRequest extends WeatherStackRequest {
    private String query;
}