package com.app.weather;

import com.app.weather.weatherApi.*;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        checkOpenWeatherApi();
        checkWeatherForecast();
    }

    public static void checkOpenWeatherApi() {
        WeatherApi map = new OpenWeatherMap();
        System.out.println(map.getResponseCode());
        System.out.println(map.getWeatherForecast("Moscow"));
    }

    public static void checkYandexWeatherApi() {
        WeatherApi map = new YandexWeatherApi();
        map.getResponseCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkGismeteoWeatherApi() {
        WeatherApi map = new GismeteoWeatherApi();
        map.getResponseCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkWeatherStackApi() {
        WeatherApi map = new WeatherStackApi();
        map.getResponseCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkWeatherForecast() {
        WeatherForecast forecast = new WeatherForecast();
        List<ForecastObject> responses = forecast.getListOfServices();
        responses.forEach(System.out::println);

        List<ForecastObject> weathers = forecast.getForecast("Moscow");
        weathers.forEach(System.out::println);
    }

}
