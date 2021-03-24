package pl.piotr_romanczak.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.piotr_romanczak.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherQuerry {
    private final Config config = new Config();
    private WeatherData weatherData;

    public WeatherQuerry(int cityId) {
        loadWeatherDataFromJSON(cityId);
    }

    public String setWeatherQuery(int cityId) {

        String weatherQuery = "http://api.openweathermap.org/data/2.5/weather?id="
                + cityId
                + "&appid="
                + config.getWeatherApiKey()
                + "&lang="
                + config.getLanguage()
                + "&units="
                + config.getUnits();
        System.out.println(weatherQuery);
        return weatherQuery;
    }

    public String getWeatherDataFromAPI(int cityId) throws IOException {
        StringBuilder result = new StringBuilder();
        URL weatherQuery = new URL(setWeatherQuery(cityId));
        URLConnection urlConnection = weatherQuery.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            result.append(str);
        }
        bufferedReader.close();
        return result.toString();
    }

    public void loadWeatherDataFromJSON(int cityId) {
        String JSONWeatherData;
        try {
            JSONWeatherData = getWeatherDataFromAPI(cityId);
            Gson gson = new Gson();
            weatherData = gson.fromJson(JSONWeatherData, new TypeToken<WeatherData>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

}
