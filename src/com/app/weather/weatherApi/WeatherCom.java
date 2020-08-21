package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * weatherapi.com
 */
public class WeatherCom extends WeatherApi {

    public WeatherCom() {
        NAME = "weatherapi.com";
        url = "http://api.weatherapi.com/v1/current.json?key=%s&q=%s&lang=ru";
        apiKey = "342b001f801745c781b164319201908";
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
            responseTemperature = parsedResponse.getJSONObject("current").getDouble("temp_c");
            responseWeather = parsedResponse.getJSONObject("current").getJSONObject("condition").getString("text");
        } catch (JSONException e) {
            // System.out.println(e.getMessage());
            return  new ForecastObject(NAME, 404);
        }
        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
