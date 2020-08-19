package com.app.weather.weatherApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for weathers api resources.
 *
 * Each class has:
 *      name of resource
 *      url address for requests
 *      api key for access to resource
 *
 * Each inherit class can:
 *      gets response code of request
 *      gets weather forecast by city which was sent.
 */
public abstract class WeatherApi {
    protected static String NAME;
    protected String url;
    protected String apiKey;

    /**
     * returns response code by default request.
     *
     * @return ForecastObject which includes name of resource and response code.
     */
    public abstract ForecastObject getResponseCode();

    /**
     * returns weather forecast of city that was sent.
     *
     * @return ForecastObject which includes weather forecast info.
     */
    public abstract ForecastObject getWeatherForecast(String city);

    /**
     * parses coordinates of requested city through openweathermap.org.
     * @param city
     */
    public List<Double> setLongitudeAndLatitude(String city) {
        String urlCoord = "http://api.openweathermap.org/data/2.5/weather?q=%s&lang=ru&units=metric&APPID=%s";
        String apiKeyCoord = "4551bc6d8820b97c86e67b7fa6186691";

        List<Double> coord = new ArrayList<>();
        double responseLongitude = 200.0;
        double responseLatitude = 100.0;
        int responseCode;
        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL(String.format(urlCoord, city, apiKeyCoord));
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
            System.out.println(e.getMessage());
            responseCode = 404;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            responseCode = 404;
        }

        if (responseCode == 200) {

            JSONObject parsedResponse;

            try {
                parsedResponse = new JSONObject(response.toString());
                responseLongitude = parsedResponse.getJSONObject("coord").getDouble("lon");
                responseLatitude = parsedResponse.getJSONObject("coord").getDouble("lat");

            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }
        }
        coord.add(responseLatitude);
        coord.add(responseLongitude);

        return coord;
    }
}
