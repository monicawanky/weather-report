package com.weather.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetCurrentWeatherRequest {
    private String city;
}