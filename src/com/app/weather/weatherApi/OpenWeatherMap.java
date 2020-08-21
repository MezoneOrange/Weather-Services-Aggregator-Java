package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * openweathermap.org
 */
public class OpenWeatherMap extends WeatherApi {

    public OpenWeatherMap() {
        NAME = "openweathermap.org";
        url = "http://api.openweathermap.org/data/2.5/weather?APPID=%s&q=%s&lang=ru&units=metric";
        apiKey = "4551bc6d8820b97c86e67b7fa6186691";
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
            responseCity = parsedResponse.getString("name");
            responseTemperature = parsedResponse.getJSONObject("main").getDouble("temp");
            responseWeather = parsedResponse.getJSONArray("weather").getJSONObject(0).getString("description");
        } catch (JSONException e) {
            //System.out.println(e.getMessage());
            return  new ForecastObject(NAME, 404);
        }

        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
