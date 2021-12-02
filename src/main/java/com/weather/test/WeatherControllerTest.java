package com.weather.test;

import com.weather.controller.WeatherController;
import com.weather.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
@ComponentScan
@ImportAutoConfiguration
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherService weatherService;

    @Test
    public void getCurrentWeatherNormalCase() throws Exception {
        mockMvc.perform(
                get("/v1/weather")
                        .param("city", "melboune")
        ).andExpect(status().isOk());
    }

    @Test
    public void getCurrentWeatherHK() throws Exception {
        mockMvc.perform(
                get("/v1/weather")
                        .param("city", "hk")
        ).andExpect(status().isOk());
    }
}