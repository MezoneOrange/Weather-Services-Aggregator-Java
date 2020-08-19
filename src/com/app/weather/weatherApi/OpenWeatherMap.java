package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMap extends WeatherApi {

    public OpenWeatherMap() {
        NAME = "openweathermap.org";
        url = "http://api.openweathermap.org/data/2.5/weather?q=%s&lang=ru&units=metric&APPID=%s";
        apiKey = "4551bc6d8820b97c86e67b7fa6186691";
    }

    @Override
    public ForecastObject getResponseCode() {
        int responseCode;
        try {
            URL obj = new URL(String.format(url, "Лондон", apiKey));
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
            URL obj = new URL(String.format(url, city, apiKey));
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
            responseCode = 404;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            responseCode = 404;
        }

        if (responseCode != 200) return new ForecastObject(NAME, responseCode);

        JSONObject parsedResponse = null;
        String responseCity = null;
        double responseTemperature = -10000.0;
        String responseWeather = null;
        try {
            parsedResponse = new JSONObject(response.toString());
            responseCity = parsedResponse.getString("name");
            responseTemperature = parsedResponse.getJSONObject("main").getDouble("temp");
            responseWeather = parsedResponse.getJSONArray("weather").getJSONObject(0).getString("description");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return  new ForecastObject(NAME, 404);
        }

        return new ForecastObject(NAME, responseCode, responseCity, responseTemperature, responseWeather);
    }
}
