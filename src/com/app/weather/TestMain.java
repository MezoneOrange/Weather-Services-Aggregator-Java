package com.app.weather;

import com.app.weather.weatherApi.*;

public class TestMain {
    public static void main(String[] args) {


    }

    public static void checkOpenWeatherApi() {
        WeatherApi map = new OpenWeatherMap();
        map.getResponceCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkYandexWeatherApi() {
        WeatherApi map = new YandexWeatherApi();
        map.getResponceCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkGismeteoWeatherApi() {
        WeatherApi map = new GismeteoWeatherApi();
        map.getResponceCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkWeatherStackApi() {
        WeatherApi map = new WeatherStackApi();
        map.getResponceCode();
        map.getWeatherForecast("Moscow");
    }

    public static void checkWeatherForecast() {
        WeatherForecast forecast = new WeatherForecast();
        forecast.getListOfServices();
        forecast.getForecast();
    }

}
