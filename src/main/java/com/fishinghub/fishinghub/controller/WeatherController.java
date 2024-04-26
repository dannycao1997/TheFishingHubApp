package com.fishinghub.fishinghub.controller;

import com.fishinghub.fishinghub.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<String> getWeather(@RequestParam String location) {
        String weatherInfo = weatherService.getWeatherInfo(location);
        if (weatherInfo.startsWith("Error:")) {
            return ResponseEntity.internalServerError().body(weatherInfo);
        }
        return ResponseEntity.ok(weatherInfo);
    }
}