package com.app.weather.weatherApi;

public class OpenWeatherMap extends WeatherApi {

    public OpenWeatherMap() {
        NAME = "openweathermap.org";
        url = "http://api.openweathermap.org/data/2.5/weather?q=%s&lang=ru&units=metric&APPID=%s";
        apiKey = "4551bc6d8820b97c86e67b7fa6186691";
    }

    @Override
    public ForecastObject getResponseCode() {
        return null;
    }

    @Override
    public ForecastObject getWeatherForecast(String city) {
        return null;
    }
}
