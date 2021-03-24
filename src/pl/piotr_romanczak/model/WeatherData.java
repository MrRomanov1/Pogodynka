package pl.piotr_romanczak.model;

import pl.piotr_romanczak.model.weather_params.Main;
import pl.piotr_romanczak.model.weather_params.Weather;
import pl.piotr_romanczak.model.weather_params.Wind;

import java.util.List;

public class WeatherData {
    private List<Weather> weather;
    private Main main;
    private Wind wind;

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public WeatherData(List<Weather> weather, Main main, Wind wind) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
    }
}
