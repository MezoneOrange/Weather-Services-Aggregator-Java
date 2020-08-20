package com.app.weather;

import com.app.weather.weatherApi.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Creates object for requests weather forecast. Object includes all weather apps.
 *
 * At first, weather forecast makes request to each weather app and get the response code.
 *
 * Then, the class asks by user to enter city name and return a current weather forecast by each weather app.
 * If weather app will not have found a city name in them database that it app not return anything.
 *
 * If user just press enter that input stream would be closed and exit from the application.
 *
 */
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((city = inputCity(reader)) != null) {
            if (city.equals("")) break;
            forecast.getForecast(city).forEach(System.out::println);
        }
        reader.close();

    }

    private static String inputCity(BufferedReader reader) throws IOException {
        System.out.print("Enter a city: ");
        String city;
        city = reader.readLine();
        return city;
    }
}
