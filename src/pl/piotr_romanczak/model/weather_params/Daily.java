package pl.piotr_romanczak.model.weather_params;

import java.util.List;

public class Daily {
    private long dt;
    private long sunrise;
    private long sunset;
    private Temp temp;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private double wind_deg;
    private List<Weather> weather;
    private int clouds;
    private double pop;
    private double rain;

    public long getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public Temp getTemp() {
        return temp;
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

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_deg() {
        return wind_deg;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public int getClouds() {
        return clouds;
    }

    public double getPop() {
        return pop;
    }

    public double getRain() {
        return rain;
    }

    public Daily(long dt, long sunrise, long sunset, Temp temp, int pressure,
                 int humidity, double dew_point, double wind_speed, double wind_deg,
                 List<Weather> weather, int clouds, double pop, double rain) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dew_point = dew_point;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weather = weather;
        this.clouds = clouds;
        this.pop = pop;
        this.rain = rain;
    }
}
