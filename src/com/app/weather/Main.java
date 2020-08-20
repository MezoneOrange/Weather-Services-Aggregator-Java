package com.app.weather;

import com.app.weather.weatherApi.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WeatherForecast forecast = new WeatherForecast(
                new OpenWeatherMap(),
                new WeatherStackApi(),
                new WeatherCom(),
                new WeatherBit()
        );

        List<ForecastObject> resources =  forecast.getListOfServices();
        resources.forEach(System.out::println);

        String city;

        while (true) {
            city = inputCity("Enter a city: ");
            if (city.equals("")) break;
            List<ForecastObject> cityForecasts = forecast.getForecast(city);
            cityForecasts.forEach(System.out::println);
        }
    }

    private static String inputCity(String prompt) throws IOException {
        System.out.print(prompt);
        String city = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        city = reader.readLine();
        reader.close();
        return city;
    }
}
