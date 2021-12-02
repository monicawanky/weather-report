package com.weather.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppEnvironment {
    @Value("${weather-stack.access-key}")
    private String weatherStackAccessKey;
}