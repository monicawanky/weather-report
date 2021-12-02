package com.weather.controller;

import com.weather.request.GetCurrentWeatherRequest;
import com.weather.response.GetCurrentWeatherResponse;
import com.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RequestMapping("v1")
@RestController("weatherCtr")
@AllArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @RequestMapping(value = "weather", method = {RequestMethod.GET, RequestMethod.POST})
    public GetCurrentWeatherResponse getWeather(@RequestParam(value = "city", defaultValue = "melboune") @NotBlank String city)
    {
        return weatherService.getWeather(new GetCurrentWeatherRequest(city));
    }
}