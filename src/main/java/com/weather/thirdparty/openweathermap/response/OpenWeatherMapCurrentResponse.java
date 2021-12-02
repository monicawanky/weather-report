package com.weather.thirdparty.openweathermap.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OpenWeatherMapCurrentResponse {
    @Data
    @AllArgsConstructor
    public static class Coor {
        private float lon;
        private float lat;
    }

    @Data
    @AllArgsConstructor
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    @AllArgsConstructor
    public static class Main {
        private float temp;
        @SerializedName("feels_like")
        private float feelsLike;
        @SerializedName("temp_min")
        private float tempMin;
        @SerializedName("temp_max")
        private float tempMax;
        @SerializedName("weather_descriptions")
        private int pressure;
        private int humidity;
    }

    @Data
    @AllArgsConstructor
    public static class Wind {
        private float speed;
        private int deg;
        private float gust;
    }

    @Data
    @AllArgsConstructor
    public static class Clouds {
        private int all;
    }

    @Data
    @AllArgsConstructor
    public static class Sys {
        private int type;
        private int id;
        private String country;
        private int sunrise;
        private int sunset;
    }

    private Coor coor;
    private Weather[] weather;
    private String base;
    private Main main;
    private Wind wind;
    private int visibility;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}