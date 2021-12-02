package com.weather.service;

import com.google.gson.Gson;
import com.weather.exception.ApiException;
import com.weather.request.GetCurrentWeatherRequest;
import com.weather.response.GetCurrentWeatherResponse;
import com.weather.thirdparty.openweathermap.service.OpenWeatherMap;
import com.weather.thirdparty.weatherstack.exception.WeatherStackInitialException;
import com.weather.thirdparty.weatherstack.service.WeatherStack;
import com.weather.util.UnitConverter;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Service("weatherSvc")
@AllArgsConstructor
public class WeatherService {
    private static final String REDIS_KEY = "weather:current";
    private RedissonClient redisson;

    public GetCurrentWeatherResponse getWeather(GetCurrentWeatherRequest request) {
        try {
            var response = getWeatherFromWeatherStack(request.getCity());
            setWeatherToRedis(response);
            return response;
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    private GetCurrentWeatherResponse getWeatherFromWeatherStack(String city) throws Exception {
        try {
            var response = WeatherStack.getCurrentWeather(city);
            return new GetCurrentWeatherResponse(response.getCurrent().getWindSpeed(), response.getCurrent().getTemperature());
        } catch (WeatherStackInitialException e) {
            throw new ApiException(e.getMessage());
        } catch (SocketTimeoutException e) {
            return getWeatherFromOpenWeatherMap();
        }
    }

    private GetCurrentWeatherResponse getWeatherFromOpenWeatherMap() throws Exception {
        try {
            var response = OpenWeatherMap.getCurrentWeather();
            return new GetCurrentWeatherResponse((int) UnitConverter.windSpeedFromMetersSecondToKilometersHour(response.getWind().getSpeed()), UnitConverter.temperatureFromKelvinToCelsius(response.getMain().getTemp()));
        } catch (SocketTimeoutException e) {
            return getWeatherFromRedis();
        }
    }

    private void setWeatherToRedis(GetCurrentWeatherResponse response) {
        var responseStr = new Gson().toJson(response);
        var cache = redisson.getBucket(REDIS_KEY);
        cache.set(responseStr);
    }

    private GetCurrentWeatherResponse getWeatherFromRedis() {
        var cache = redisson.getBucket(REDIS_KEY);
        if (cache.isExists()) {
            return new Gson().fromJson((String)cache.get(), GetCurrentWeatherResponse.class);
        } else {
            throw new ApiException("Cannot get weather data");
        }
    }
}