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
            if (city.equals(""))  {
                System.out.println("That's all. Have a nice day. Good bye.");
                break;
            }
            int finded = 0;
            List<ForecastObject> weathers = forecast.getForecast(city);
            for (ForecastObject weather : weathers) {
                if (weather.getRESPONSE() == 200) {
                    finded++;
                    System.out.println(weather);
                }
            }
            if (finded == 0) {
                System.out.println("City has not been found.\n");
            }
        }
        reader.close();

    }

    /**
     * Asks user to enter a city and returns a result.
     *
     * @param reader input stream.
     * @return string that user was sent.
     * @throws IOException throw next.
     */
    private static String inputCity(BufferedReader reader) throws IOException {
        System.out.println("Just press Enter if you want to finish.");
        System.out.print("Enter a city: ");
        String city;
        city = reader.readLine();
        System.out.println();
        return city;
    }
}
