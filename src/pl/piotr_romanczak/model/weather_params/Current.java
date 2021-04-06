package pl.piotr_romanczak.model.weather_params;

import java.util.List;

public class Current {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    private double feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private int clouds;
    private double wind_speed;
    private double wind_deg;
    private List<Weather> weather;
    private Rain rain;
    private long visibility;

    public long getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getDew_point() {
        return dew_point;
    }

    public int getClouds() {
        return clouds;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_deg() {
        return wind_deg;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Rain getRain() {
        return rain;
    }

    public Current(long dt, long sunrise, long sunset, double temp, double feels_like, int pressure, int humidity, double dew_point, int clouds, double wind_speed, double wind_deg, List<Weather> weather, Rain rain, long visibility) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dew_point = dew_point;
        this.clouds = clouds;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weather = weather;
        this.rain = rain;
        this.visibility = visibility;
    }

    public long getVisibility() {
        return visibility;
    }
}
