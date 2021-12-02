package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCurrentWeatherResponse {
    private int windSpeed;

    private float temperatureDegrees;
}