# Weather Services Aggregator

The aggregator uses next resources:
- https://openweathermap.org
- https://weatherstack.com
- https://weather.com
- https://www.weatherbit.io  

#
weatherApp.jar - executable .jar archive GUI version of programme.

#
    
    Important!: I strongly recommend to use your API KEY for each resources. You can change it in constructor of each resource's class.
    My keys could not work because some of them has a daily limitations.
    
    Important!: Because each weather resource returns JSON object by request application uses java-json.jar library. The repository contains library in root folder.

#

### WeatherApi abstract class and his inheritors
    
For each resource created same name class, that based on WeatherApi abstract class. Each resource has same functionality which is set in the parent class.

In constructor sets name of resource, template of url and api key.
Provides next methods:
- `getResponseCode()` returns a response's status code of resource.
- `getWeatherForecast(String city)` has the `String` input parameter - city name. Returns a current weather forecast for city, that would be sent. Returns - name of resource, response code, name of city, his temperature, and weather condition. If city would not find in the resource's database, that returns name of resource and response code. Other fields would have default values. Uses java-json.jar library for parsing JSON object that resource returns.

Both methods return ForecastObject object.
#

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
#

### WeatherForecast class

The class works with all weather resources at the same time.

Has fields:
    `List<WeatherApi> resources` - contains `WeatherApi` objects that response code was 200.
    `passedResources` - count of resources that was sent in the constructor
    `availableResources` - count of resources that was added to `resources`.

The constructor of class takes array of `WeatherApi` objects, checks their response code, and adds to `resources`. Also sets `passedResources` and `availableResources` fields.

Has four methods:
1. `getListOfServices()` returns List of `ForecastObject` objects that contains resources' name, and their response code.
2. `getForecast(String city)` returns List of `ForecastObject` objects that contains weather condition from each resource or, if response code of some resource was not 200, contains only resource' name, and their response code.
3. `getPassedResources()` returns `passedResources` field.
4. `getAvailableResources()` returns `availableResources` field.
#

### GUIVersionMain class (new version)

Graphical User Interface version of programme.

The class creates a `WeatherForecast` instance that contains instances of each weather resource that had response code 200.

Works with Singleton class `WeatherGui`.

### WeatherGui class

Singleton class that contains all realisation of GUI.

GUI contains 3 areas:
 
      1. Upper area implements the accept city's name and sends request to api's.
          `User inputs a city's name into the text field and either press "Enter" or click by button
           for that request was sent`
 
      2. Middle area displays result of request that was sent from Upper area.
         When app just would open, it displays names of resources that is available on the moment of start.

      3. Bottom area displays how many resources is available on the moment of start.
          `In the time of work the programme, app doesn't restart resources
           that was not available n the moment of start. If you want to get access
           to resources that not available, try to restart the programme.`

#

### ConsoleVersionMain class (old version)

Console version of programme.

Contains realisation when user inputs the city name and application returns a weather forecast from each resource that has found a city.

The class creates a `WeatherForecast` instance that contains instances of each weather resource that had response code 200.

Prints response code of each resource through method `getListOfServices()`.

Creates the stream that read input that would send through the terminal. Asks the user to input a city name and returns weather forecast from each resource if it was found through method `getForecast(String city)`. Asks while user don't send an empty string. When user enters an empty string, stream will be closed and application exits.

#
#
###### author Dmitry Shelukhin