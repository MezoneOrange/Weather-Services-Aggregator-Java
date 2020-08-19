package com.app.weather.weatherApi;

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
    protected String NAME;
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

}
