package pl.piotr_romanczak;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import pl.piotr_romanczak.controller.ErrorMessages;
import pl.piotr_romanczak.controller.Statements;
import pl.piotr_romanczak.model.Geolocation;
import pl.piotr_romanczak.model.LocationData;
import pl.piotr_romanczak.model.LocationLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pogodynka {
    private List<LocationData> citiesList;
    private Map<String, String> cityNames;
    private String cityNameWithCountryCodeFromGeoLoc;

    public Pogodynka() {
        setCityListWithCountryCodes();
        try {
            Geolocation geolocation = new Geolocation();
            cityNameWithCountryCodeFromGeoLoc = geolocation.getCityNameWithCountryCodeFromGeoLoc();
        } catch (IOException e) {
            ErrorMessages.setErrorMessage(Statements.CANNOT_CHECK_LOCATION);
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            ErrorMessages.setErrorMessage(Statements.CANNOT_CHECK_LOCATION);
            e.printStackTrace();
        }
    }

    public List<LocationData> getCitiesList() {
        return citiesList;
    }

    public Map<String, String> getCityNames() {
        return cityNames;
    }

    public void setCityListWithCountryCodes() {
        try {
            citiesList = new LocationLoader().getCitiesList();
            cityNames = new HashMap<>();
            for (LocationData locationData : citiesList) {
                String city = locationData.getCityName();
                String countryCode = locationData.getCountryCode();
                cityNames.put(city, city + ", " + countryCode);
            }
        } catch (IOException e) {
            ErrorMessages.setErrorMessage(Statements.CANNOT_GET_CITY_DB);
            e.printStackTrace();
        } catch (Exception e) {
            ErrorMessages.setErrorMessage(Statements.CANNOT_GET_CITY_DB);
            e.printStackTrace();
        }
    }

    public String getCityNameWithCountryCodeFromGeoLoc() {
        return cityNameWithCountryCodeFromGeoLoc;
    }
}


