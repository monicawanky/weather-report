package com.weather.util;

import java.text.DecimalFormat;

public class UnitConverter {
    public static float temperatureFromKelvinToCelsius(float kelvinTemperature) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(kelvinTemperature - 273.15F));
    }

    public static float windSpeedFromMetersSecondToKilometersHour(float msWindSpeed) {
        return (float) (3.6 * msWindSpeed);
    }
}