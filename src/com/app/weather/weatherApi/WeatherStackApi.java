package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * weatherstack.com
 */
public class WeatherStackApi extends WeatherApi {

    public WeatherStackApi() {
        NAME = "weatherstack.com";
        url = "http://api.weatherstack.com/current?access_key=%s&query=%s";
        apiKey = "876152b1b7b6c6feae10f16c7a964268";
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
            responseCity = parsedResponse.getJSONObject("location").getString("name");
            responseTemperature = parsedResponse.getJSONObject("current").getDouble("temperature");
            responseWeather = parsedResponse.getJSONObject("current").getJSONArray("weather_descriptions").getString(0);
        } catch (JSONException e) {
            // System.out.println(e.getMessage());
            return new ForecastObject(NAME, 404);
        }
        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
