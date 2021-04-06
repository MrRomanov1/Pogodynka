package pl.piotr_romanczak;

import pl.piotr_romanczak.model.LocationData;
import pl.piotr_romanczak.model.LocationLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Pogodynka {
    private List<LocationData> citiesList;
    private HashMap<String, String> cityNames;

    public Pogodynka() {
        getCityListWithCountryCodes();
    }

    public List<LocationData> getCitiesList() {
        return citiesList;
    }

    public HashMap<String, String> getCityNames() {
        return cityNames;
    }

    public void getCityListWithCountryCodes() {
        try {
            citiesList = new LocationLoader().getCitiesList();
            cityNames = new HashMap<>();
            for (LocationData locationData : citiesList) {
                String city = locationData.getCityName();
                String countryCode = locationData.getCountryCode();
                cityNames.put(city, city + ", " + countryCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


