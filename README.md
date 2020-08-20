# Weather Services Aggregator

The aggregator uses next resources:
- https://openweathermap.org
- https://weatherstack.com
- https://weather.com
- https://www.weatherbit.io
    
    
    Important!: I strongly recommend to use your API KEY for each resources. You can change it in constructor of each resource's class.
    My keys could not work because some of them has a daily limitations.
    
    Important!: Because each weather resource returns JSON object by request application uses java-json.jar library. The repository contains library in root folder.

### WeatherApi abstract class and his inheritors
    
For each resource created same name class, that based on WeatherApi abstract class. Each resource has same functionality which is set in the parent class.

In constructor sets name of resource, template of url and api key.
Provides next methods:
- `getResponseCode()` returns a response's status code of resource.
- `getWeatherForecast(String city)` has the `String` input parameter - city name. Returns a current weather forecast for city, that would be sent. Returns - name of resource, response code, name of city, his temperature, and weather condition. If city would not find in the resource's database, that returns name of resource and response code. Other fields would have default values. Uses java-json.jar library for parsing JSON object that resource returns.

Both methods return ForecastObject object.

### ForecastObject class

Each method of resource returns the ForecastObject object that has next fields:
- `String RESOURCE` - name of resource that was used.
- `int RESPONSE` - response code of resource.
- `String CITY` - name of city, that was returned by request.
- `double TEMPERATURE` - current temperature of requested city.
- `String WEATHER` - current weather condition of requested city.

The object has two type of constructors:
1. Receives two parameters: name of resource (`String`), and his response code (`int`). Other fields would have default values.
2. Receives five parameters: name of resource (`String`), his response code (`int`), city name (`String`), his current temperature (`double`), and his weather condition (`String`).

Has overridden method `toString()` that has two options:
1. If has been used the constructor with two parameters, that makes the `String` with name of resource and response code.
2. If has been used the constructor with five parameters, that makes the `String` with current weather forecast of requested city.

### WeatherForecast class

The class works with all weather resources at the same time.

Has one field - `List<WeatherApi> resources`

The constructor of class takes array of `WeatherApi` objects and adds them to List.

Has two methods:
1. `getListOfServices()` returns List of `ForecastObject` objects that contains resources' name, and their response code.
2. `getForecast(String city)` returns List of `ForecastObject` objects that contains weather condition from each resource or, if response code of some resource was not 200, contains only resource' name, and their response code.

### Main class

Contains realisation when user inputs the city name and application returns a weather forecast from each resource that has found a city.

The class creates a `WeatherForecast` instance that contains instances of each weather resource.

Prints response code of each resource through method `getListOfServices()`.

Creates the stream that read input that would send through the terminal. Asks the user to input a city name and returns weather forecast from each resource if it was found through method `getForecast(String city)`. Asks while user don't send an empty string. When user enters an empty string, stream will be closed and application exits.


###### author Dmitry Shelukhin