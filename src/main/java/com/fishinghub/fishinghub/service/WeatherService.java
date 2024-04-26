package com.fishinghub.fishinghub.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String apiKey = "8aeb8bb2a9msh70568e34f2c6b5cp112885jsn1389740f4cb6"; // Replace with your actual API key

    public String getWeatherInfo(String location) {
        try {
            HttpResponse<String> response = Unirest.get("https://weatherapi-com.p.rapidapi.com/current.json?q=53.1%2C-0.13")
                    .queryString("q", location)
                    .header("X-RapidAPI-Key", apiKey)
                    .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .asString();

            if (response.getStatus() == 200) {
                return response.getBody();
            } else {
                // Handle API error responses
                return String.format("Failed to fetch weather data: %s", response.getStatusText());
            }
        } catch (UnirestException e) {
            return "Error: Unable to fetch weather data";
        }
    }
}