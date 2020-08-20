package com.app.weather;

import com.app.weather.weatherApi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that works with weather resources.
 */
public class WeatherForecast {
    private List<WeatherApi> resources = new ArrayList<>();

    /**
     * Creates list of resources by one instance.
     */
    public WeatherForecast(WeatherApi... weatherApps) {
        Collections.addAll(resources, weatherApps);
    }

    /**
     * Returns list of resources that contains their status code and name of resource.
     *
     * @return List<ForecastObject> which includes names of resources and their status code.
     */
    public List<ForecastObject> getListOfServices() {
        List<ForecastObject> responses = new ArrayList<>();
        for (WeatherApi resource : resources) {
            responses.add(resource.getResponseCode());
        }
        return responses;
    }

    /**
     * Returns list of weather info from each resource by city that was sent.
     *
     * @return List<ForecastObject> which includes weather info from each resource.
     */
    public List<ForecastObject> getForecast(String city) {
        List<ForecastObject> forecast = new ArrayList<>();
        for (WeatherApi resource : resources) {
            forecast.add(resource.getWeatherForecast(city));
        }
        return forecast;
    }
}
