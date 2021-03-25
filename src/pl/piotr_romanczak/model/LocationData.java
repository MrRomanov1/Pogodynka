package pl.piotr_romanczak.model;

public class LocationData {
    private int id;
    private String name;
    private String country;
    private Coord coord;

    public LocationData(int id, String name, String country, Coord coord) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return name;
    }

    public String getCountryCode() {
        return country;
    }
}

