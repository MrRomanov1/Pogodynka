package pl.piotr_romanczak.model;
import pl.piotr_romanczak.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherData {
    private final Config config = new Config();

    public String setWeatherQuery() {

        String weatherQuery = "http://api.openweathermap.org/data/2.5/weather?q="
                + "London"
                + "&appid="
                + config.getWeatherApiKey()
                + "&lang="
                + config.getLanguage()
                +"&units="
                + config.getUnits();
        System.out.println(weatherQuery);
        return weatherQuery;
    }

    public void getWeatherData () throws IOException {
        StringBuilder result = new StringBuilder();
        URL weatherQuery = new URL(setWeatherQuery());
        URLConnection urlConnection = weatherQuery.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            result.append(str);
        }
        bufferedReader.close();
        System.out.println(result);
    }
}
