package com.app.weather;

import com.app.weather.weatherApi.*;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        checkWeatherForecast();
    }

    public static void checkOpenWeatherApi() {
        WeatherApi map = new OpenWeatherMap();
        System.out.println(map.getResponseCode());
        System.out.println(map.getWeatherForecast("Москва"));
    }

    public static void checkWeatherStackApi() {
        WeatherApi map = new WeatherStackApi();
        System.out.println(map.getResponseCode());
        System.out.println(map.getWeatherForecast("Москва"));
    }

    public static void checkWeatherCom() {
        WeatherApi map = new WeatherCom();
        System.out.println(map.getResponseCode());
        System.out.println(map.getWeatherForecast("Москва"));
    }

    public static void checkWeatherBit() {
        WeatherApi map = new WeatherBit();
        System.out.println(map.getResponseCode());
        System.out.println(map.getWeatherForecast("Москва"));
    }

    public static void checkWeatherForecast() {
        WeatherForecast forecast = new WeatherForecast();
        List<ForecastObject> responses = forecast.getListOfServices();
        responses.forEach(System.out::println);

        List<ForecastObject> weathers = forecast.getForecast("miami");
        weathers.forEach(System.out::println);
    }

}
