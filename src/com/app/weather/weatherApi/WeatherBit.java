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
            parsedResponse = parsedResponse.getJSONArray("data").getJSONObject(0);
            responseCity = parsedResponse.getString("city_name");
            responseTemperature = parsedResponse.getDouble("temp");
            responseWeather = parsedResponse.getJSONObject("weather").getString("description");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return  new ForecastObject(NAME, 404);
        }
        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
