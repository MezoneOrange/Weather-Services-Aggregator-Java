package com.app.weather.weatherApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Abstract class for weathers api resources.
 *
 * Each class has:
 *      name of resource
 *      url address for requests. Urls must contain next get parameters spots: api key, city name. Only in this order.
 *      api key for access to resource
 *      responseCode that sets when makes a request. By default - 404.
 *
 * Each inherit class can:
 *      gets response code of request
 *      gets weather forecast by city which was sent.
 */
public abstract class WeatherApi {
    protected String NAME;
    protected String url;
    protected String apiKey;
    protected int responseCode = 404;

    /**
     * returns response code by default request.
     *
     * @return ForecastObject which includes name of resource and response code.
     */
    public ForecastObject getResponseCode() {
        try {
            URL obj = new URL(String.format(url, apiKey, "London"));
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            responseCode = connection.getResponseCode();
        } catch (MalformedURLException e) {
            responseCode = 404;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            responseCode = 404;
        }

        return new ForecastObject(NAME, responseCode);
    }

    /**
     * returns weather forecast of city that was sent.
     *
     * @return ForecastObject which includes weather forecast info.
     */
    public abstract ForecastObject getWeatherForecast(String city);

    /**
     * Returns response from a weather resource by a city that was sent.
     * @param city that is requested.
     * @return StringBuilder object of data that was returned.
     */
    protected StringBuilder getResponse(String city) {
        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL(String.format(url, apiKey, URLEncoder.encode(city, StandardCharsets.UTF_8)));
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
        return response;
    }
}
