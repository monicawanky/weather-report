package com.weather.thirdparty.weatherstack.service;

import com.google.gson.Gson;
import com.weather.thirdparty.weatherstack.exception.WeatherStackInitialException;
import com.weather.thirdparty.weatherstack.response.WeatherStackCurrentResponse;
import com.weather.util.City;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

public class WeatherStack {
    private String accessKey;

    private static WeatherStack weatherStack = null;

    private static final String URL_SCHEMA = "http";
    private static final String URL_HOST = "api.weatherstack.com";

    public static final long TIME_OUT_SECOND = 3;

    public WeatherStack(String accessKey) {
        this.accessKey = accessKey;
    }

    private static void initialValidate() throws WeatherStackInitialException {
        if (weatherStack == null) {
            throw new WeatherStackInitialException();
        }
    }

    public static void initial(String accessKey) throws WeatherStackInitialException {
        if (StringUtils.isBlank(accessKey)) {
            throw new WeatherStackInitialException();
        }

        if (weatherStack == null) {
            synchronized (WeatherStack.class) {
                if (weatherStack == null) {
                    weatherStack = new WeatherStack(accessKey);
                }
            }
        }
    }

    public static WeatherStack getInstance() throws WeatherStackInitialException {
        initialValidate();
        return weatherStack;
    }

    private static OkHttpClient getClient() {
        File httpCacheDirectory = new File("./src/main/java/com/cache");
        int cacheSize = 10 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
                .build();
    }

    public static WeatherStackCurrentResponse getCurrentWeather(String requestCity) throws WeatherStackInitialException, SocketTimeoutException, IOException {
        initialValidate();
        final String API_PATH = "/current";
        var city = City.valueOfName(requestCity);

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(URL_SCHEMA)
                .host(URL_HOST)
                .addPathSegment(API_PATH)
                .addQueryParameter("access_key", WeatherStack.getInstance().accessKey)
                .addQueryParameter("query", city.getWeatherStackName())
                .build();

        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .cacheControl(new CacheControl.Builder().maxAge(30, TimeUnit.SECONDS).build())
                .url(httpUrl)
                .build();

        var httpResponse = getClient().newCall(requesthttp).execute();
        var httpResponseStr = httpResponse.body().string();
        return new Gson().fromJson(httpResponseStr, WeatherStackCurrentResponse.class);
    }
}