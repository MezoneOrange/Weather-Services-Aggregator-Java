package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * weatherbit.io
 */
public class WeatherBit extends WeatherApi {

    public WeatherBit() {
        NAME = "weatherbit.io";
        url = "https://api.weatherbit.io/v2.0/current?key=%s&city=%s&lang=ru";
        apiKey = "d6eb8cadd59f4d298de5f0985015a859";
    }

    @Override
    public ForecastObject getWeatherForecast(String city) {
        StringBuilder response = getResponse(city);

        if (responseCode != 200) return new ForecastObject(NAME, responseCode);

        JSONObject parsedResponse;
        String responseCity;
        double responseTemperature;
        String responseWeather;
        try {
            parsedResponse = new JSONObject(response.toString());
            parsedResponse = parsedResponse.getJSONArray("data").getJSONObject(0);
            responseCity = parsedResponse.getString("city_name");
            responseTemperature = parsedResponse.getDouble("temp");
            responseWeather = parsedResponse.getJSONObject("weather").getString("description");
        } catch (JSONException e) {
            // System.out.println(e.getMessage());
            return  new ForecastObject(NAME, 404);
        }
        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
