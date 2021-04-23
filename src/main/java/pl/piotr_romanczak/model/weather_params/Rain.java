package pl.piotr_romanczak.model.weather_params;

public class Rain {
    double nextHour;

    public Rain(double nextHour) {
        this.nextHour = nextHour;
    }

    public double getNextHour() {
        return nextHour;
    }
}

