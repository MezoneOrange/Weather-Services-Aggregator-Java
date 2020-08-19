package com.app.weather.weatherApi;

/**
 * weather object that includes info that was sent from a weather resource.
 */
public class ForecastObject {
    private final String RESOURCE;
    private final int RESPONSE;
    private final String CITY;
    private final double TEMPERATURE;
    private final String WEATHER;

    /**
     * Constructor that uses for return only the status code of resource, or if status code was not 200.
     * @param resource's name
     * @param response code of request.
     */
    public ForecastObject(String resource, int response) {
        RESOURCE = resource;
        RESPONSE = response;
        CITY = null;
        TEMPERATURE = -10000.0;
        WEATHER = null;
    }

    /**
     * When response code of a resource was 200 returns full info by city that was sent.
     * @param resource's name
     * @param response code of request
     * @param city's name
     * @param temperature of weather in a city
     * @param weather status
     */
    public ForecastObject(String resource, int response, String city, double temperature, String weather) {
        RESOURCE = resource;
        RESPONSE =response;
        CITY = city;
        TEMPERATURE = temperature;
        WEATHER = weather;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("--------------------\n");
        if (CITY == null) {
            if (RESPONSE == 200) {
                result.append(String.format("|  %s response code is %d\n", RESOURCE, RESPONSE));
            } else {
                result.append(String.format("|  %s response code is %d. Some problems.\n", RESOURCE, RESPONSE));
            }
        } else {
            result.append(String.format("|  %s\n|  \n|  %s %.1f C˚\n|  %s\n", RESOURCE, CITY, TEMPERATURE, WEATHER));
        }
        result.append("--------------------\n");
        return result.toString();
    }

    public String getRESOURCE() {
        return RESOURCE;
    }

    public int getRESPONSE() {
        return RESPONSE;
    }

    public String getCITY() {
        return CITY;
    }

    public double getTEMPERATURE() {
        return TEMPERATURE;
    }

    public String getWEATHER() {
        return WEATHER;
    }
}
