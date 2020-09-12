package com.app.weather;

import com.app.weather.weatherApi.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that works with weather resources.
 */
public class WeatherForecast {
    private List<WeatherApi> resources = new ArrayList<>();
    private int passedResources;
    private int availableResources;

    /**
     * Creates list of resources by one instance.
     * Adds services that response code was 200.
     * Sets fields:
     *      passedResources - how many resources was passed
     *      availableResources - how many resources had response code 200 and was added to resources list.
     */
    public WeatherForecast(WeatherApi... weatherApps) {
        passedResources = weatherApps.length;
        for (WeatherApi app : weatherApps) {
            if (app.getResponseCode().getRESPONSE() == 200) {
                resources.add(app);
            }
        }
        availableResources = resources.size();
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

    public int getPassedResources() {
        return passedResources;
    }

    public int getAvailableResources() {
        return availableResources;
    }

}
