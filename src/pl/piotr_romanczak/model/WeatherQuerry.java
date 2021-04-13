package pl.piotr_romanczak.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.piotr_romanczak.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class WeatherQuerry {
    private final Config config = new Config();
    private WeatherData weatherData;

    public WeatherQuerry(List<Double> cityParams) {
        loadWeatherDataFromJSON(cityParams);
    }

    public String setWeatherQuery(List<Double> cityParams) {

        String weatherQuery = "http://api.openweathermap.org/data/2.5/onecall?lat="
                + cityParams.get(0)
                + "&lon="
                + cityParams.get(1)
                + "&exclude=minutely,hourly"
                + "&appid="
                + config.getWeatherApiKey()
                + "&lang="
                + config.getLanguage()
                + "&units="
                + config.getUnits();
        return weatherQuery;
    }

    public String getWeatherDataFromAPI(List<Double> cityParams) throws IOException {
        StringBuilder result = new StringBuilder();
        URL weatherQuery = new URL(setWeatherQuery(cityParams));
        URLConnection urlConnection = weatherQuery.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            result.append(str);
        }
        bufferedReader.close();
        return result.toString();
    }

    public void loadWeatherDataFromJSON(List<Double> cityParams) {
        String JSONWeatherData;
        try {
            JSONWeatherData = getWeatherDataFromAPI(cityParams);
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
