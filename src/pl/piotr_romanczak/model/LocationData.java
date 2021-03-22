package pl.piotr_romanczak.model;

public class LocationData {
    private int id;
    private String name;
    private String country;

    public LocationData(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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
