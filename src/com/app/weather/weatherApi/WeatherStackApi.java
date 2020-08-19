package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherStackApi extends WeatherApi {

    public WeatherStackApi() {
        NAME = "weatherstack.com";
        url = "http://api.weatherstack.com/current?access_key=%s&query=%s";
        apiKey = "876152b1b7b6c6feae10f16c7a964268";
    }

    @Override
    public ForecastObject getResponseCode() {
        int responseCode;
        try {
            URL obj = new URL(String.format(url, apiKey, "London"));
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            responseCode = connection.getResponseCode();
        } catch (MalformedURLException e) {
            responseCode = 404;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            responseCode = 404;
        }

        return new ForecastObject(NAME, responseCode);
    }

    @Override
    public ForecastObject getWeatherForecast(String city) {
        int responseCode;
        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL(String.format(url, apiKey, URLEncoder.encode(city, StandardCharsets.UTF_8)));
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            responseCode = 404;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            responseCode = 404;
        }

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
            System.out.println(e.getMessage());
            return  new ForecastObject(NAME, 404);
        }
        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
