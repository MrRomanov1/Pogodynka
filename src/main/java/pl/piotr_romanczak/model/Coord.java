package pl.piotr_romanczak.model;

public class Coord {
    private double lat;
    private double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public Coord(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
