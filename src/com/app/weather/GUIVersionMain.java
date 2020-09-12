package com.app.weather;

import com.app.weather.gui.WeatherGui;
import com.app.weather.weatherApi.OpenWeatherMap;
import com.app.weather.weatherApi.WeatherBit;
import com.app.weather.weatherApi.WeatherCom;
import com.app.weather.weatherApi.WeatherStackApi;


/**
 * New version of programme now supports the Graphical User Interface (GUI).
 *
 * GUI contains 3 areas:
 *
 *      1. Upper area implements the accept city's name and sends request to api's.
 *          `User inputs a city's name into the text field and either press "Enter" or click by button
 *           for that request was sent`
 *
 *      2. Middle area displays result of request that was sent from Upper area.
 *         When app just would open, it displays names of resources that is available on the moment of start.
 *
 *      3. Bottom area displays how many resources is available on the moment of start.
 *          `In the time of work the programme, app doesn't restart resources
 *           that was not available n the moment of start. If you want to get access
 *           to resources that not available, try to restart the programme.`
 */
public class GUIVersionMain {
    public static void main(String[] args) {
        WeatherForecast forecast = new WeatherForecast(
                new OpenWeatherMap(),
                new WeatherStackApi(),
                new WeatherCom(),
                new WeatherBit()
        );

        WeatherGui gui = WeatherGui.getInstance();
        gui.go(forecast);
    }
}
