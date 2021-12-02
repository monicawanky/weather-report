package com.weather.thirdparty.weatherstack.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeatherStackCurrentResponse {
    @Data
    @AllArgsConstructor
    public static class Request {
        private String type;
        private String query;
        private String language;
        private String unit;
    }

    @Data
    @AllArgsConstructor
    public static class Location {
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        @SerializedName("timezone_id")
        private String timezoneId;
        private String localtime;
        @SerializedName("localtime_epoch")
        private long localtimeEpoch;
        @SerializedName("utc_offset")
        private String utcOffset;;
    }

    @Data
    @AllArgsConstructor
    public static class Current {
        @SerializedName("observation_time")
        private String observationTime;
        private float temperature;
        @SerializedName("weather_code")
        private int weatherCode;
        @SerializedName("weather_icons")
        private String[] weatherIcons;
        @SerializedName("weather_descriptions")
        private String[] weatherDescriptions;
        @SerializedName("wind_speed")
        private int windSpeed;
        @SerializedName("wind_degree")
        private int windDegree;
        @SerializedName("wind_dir")
        private String windDir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        @SerializedName("uv_index")
        private int uvIndex;
        private int visibility;
        @SerializedName("is_day")
        private String isDay;
    }

    private Request request;
    private Location location;
    private Current current;
}