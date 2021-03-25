package pl.piotr_romanczak.model;

import pl.piotr_romanczak.model.weather_params.Current;
import pl.piotr_romanczak.model.weather_params.Weather;
import pl.piotr_romanczak.model.weather_params.Daily;

import java.util.List;

public class WeatherData {
    private List<Weather> weather;
    private Current current;
    private List<Daily> daily;

    public List<Weather> getWeather() {
        return weather;
    }

    public Current getCurrent() {
        return current;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public WeatherData(List<Weather> weather, Current current, List<Daily> daily) {
        this.weather = weather;
        this.current = current;
        this.daily = daily;
    }
}
