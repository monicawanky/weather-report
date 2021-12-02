package com.weather.thirdparty.openweathermap.service;

import com.google.gson.Gson;
import com.weather.thirdparty.openweathermap.response.OpenWeatherMapCurrentResponse;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenWeatherMap {
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=melbourne,AU&appid=2326504fb9b100bee21400190e4dbe6d";
    public static final long TIME_OUT_SECOND = 3;

    private static OkHttpClient getClient() {
        File httpCacheDirectory = new File("./src/main/java/com/cache");
        int cacheSize = 10 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
                .build();
    }

    public static OpenWeatherMapCurrentResponse getCurrentWeather() throws IOException {
        Request httpRequest = new Request.Builder()
                .addHeader("accept", "application/json")
                .cacheControl(new CacheControl.Builder().maxAge(3, TimeUnit.SECONDS).build())
                .url(URL)
                .build();

        var httpResponse = getClient().newCall(httpRequest).execute();
        var httpResponseStr = httpResponse.body().string();

        return new Gson().fromJson(httpResponseStr, OpenWeatherMapCurrentResponse.class);
    }
}