package com.weather.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum City {
    MELBOURNE("melboune", "Melbourne"),
    HONG_KONG("hk", "HongKong"),
    ;

    private String name;
    private String weatherStackName;

    public static City valueOfName(String name) {
        return Arrays.stream(City.values()).filter(status -> status.name.equals(name))
                .findFirst()
                .orElse(City.MELBOURNE);
    }
}