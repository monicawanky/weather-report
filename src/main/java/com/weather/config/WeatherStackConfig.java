package com.weather.config;

import com.weather.thirdparty.weatherstack.exception.WeatherStackInitialException;
import com.weather.thirdparty.weatherstack.service.WeatherStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "weather-stack")
@Configuration
public class WeatherStackConfig {
    @Autowired
    private AppEnvironment env;

    @Bean
    public WeatherStack weatherStackInit() throws WeatherStackInitialException {
        WeatherStack.initial(env.getWeatherStackAccessKey());
        return WeatherStack.getInstance();
    }
}