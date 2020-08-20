package com.app.weather;

import com.app.weather.weatherApi.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>(Arrays.asList("Москва", "санкт петербург",
                                                            "miami", "ревда", "екат", "new york"));
        cities.forEach(TestMain::checkWeatherBit);
    }

    public static void checkOpenWeatherApi(String city) {
        WeatherApi map = new OpenWeatherMap();
        ForecastObject obj = map.getResponseCode();

        System.out.println(obj.getRESOURCE());
        System.out.println(obj.getRESPONSE());
        System.out.println(obj.getCITY());
        System.out.println(obj.getTEMPERATURE());
        System.out.println(obj.getWEATHER());
        System.out.println(obj);

        ForecastObject objCity = map.getWeatherForecast(city);

        System.out.println(objCity.getRESOURCE());
        System.out.println(objCity.getRESPONSE());
        System.out.println(objCity.getCITY());
        System.out.println(objCity.getTEMPERATURE());
        System.out.println(objCity.getWEATHER());
        System.out.println(objCity);
    }

    public static void checkWeatherStackApi(String city) {
        WeatherApi map = new WeatherStackApi();
        ForecastObject obj = map.getResponseCode();

        System.out.println(obj.getRESOURCE());
        System.out.println(obj.getRESPONSE());
        System.out.println(obj.getCITY());
        System.out.println(obj.getTEMPERATURE());
        System.out.println(obj.getWEATHER());
        System.out.println(obj);

        ForecastObject objCity = map.getWeatherForecast(city);

        System.out.println(objCity.getRESOURCE());
        System.out.println(objCity.getRESPONSE());
        System.out.println(objCity.getCITY());
        System.out.println(objCity.getTEMPERATURE());
        System.out.println(objCity.getWEATHER());
        System.out.println(objCity);
    }

    public static void checkWeatherCom(String city) {
        WeatherApi map = new WeatherCom();
        ForecastObject obj = map.getResponseCode();

        System.out.println(obj.getRESOURCE());
        System.out.println(obj.getRESPONSE());
        System.out.println(obj.getCITY());
        System.out.println(obj.getTEMPERATURE());
        System.out.println(obj.getWEATHER());
        System.out.println(obj);

        ForecastObject objCity = map.getWeatherForecast(city);

        System.out.println(objCity.getRESOURCE());
        System.out.println(objCity.getRESPONSE());
        System.out.println(objCity.getCITY());
        System.out.println(objCity.getTEMPERATURE());
        System.out.println(objCity.getWEATHER());
        System.out.println(objCity);
    }

    public static void checkWeatherBit(String city) {
        WeatherApi map = new WeatherBit();
        ForecastObject obj = map.getResponseCode();

        System.out.println(obj.getRESOURCE());
        System.out.println(obj.getRESPONSE());
        System.out.println(obj.getCITY());
        System.out.println(obj.getTEMPERATURE());
        System.out.println(obj.getWEATHER());
        System.out.println(obj);

        ForecastObject objCity = map.getWeatherForecast(city);

        System.out.println(objCity.getRESOURCE());
        System.out.println(objCity.getRESPONSE());
        System.out.println(objCity.getCITY());
        System.out.println(objCity.getTEMPERATURE());
        System.out.println(objCity.getWEATHER());
        System.out.println(objCity);
    }

    public static void checkWeatherForecast(String city) {
        WeatherForecast forecast = new WeatherForecast(
                new OpenWeatherMap(),
                new WeatherStackApi(),
                new WeatherCom(),
                new WeatherBit()
        );
        List<ForecastObject> responses = forecast.getListOfServices();
        responses.forEach(System.out::println);

        List<ForecastObject> weathers = forecast.getForecast(city);
        for (ForecastObject weather : weathers) {
            if (weather.getRESPONSE() == 200) {
                System.out.println(weather);
            }
        }
    }

}
