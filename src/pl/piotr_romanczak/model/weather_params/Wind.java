package pl.piotr_romanczak.model.weather_params;

public class Wind {
    private double speed;
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public Wind(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }
}
