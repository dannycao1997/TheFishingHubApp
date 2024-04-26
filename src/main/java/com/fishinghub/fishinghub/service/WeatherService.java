package com.fishinghub.fishinghub.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    public String getWeatherInfo(String location) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=your_api_key&q=" + location;
        return restTemplate.getForObject(apiUrl, String.class);
    } // reminder to finish weather api key
}