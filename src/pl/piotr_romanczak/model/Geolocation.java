package pl.piotr_romanczak.model;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class Geolocation {
    private String cityNameWithCountryCodeFromGeoLoc;

    public Geolocation() throws IOException, GeoIp2Exception {
        setCityWithCountryCodeFromGeoLoc();
    }

    public void setCityWithCountryCodeFromGeoLoc() throws IOException, GeoIp2Exception {

        File database = new File("src\\pl\\piotr_romanczak\\resources\\GeoLite2-City.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        InetAddress ipAddress = InetAddress.getByName(getMyIpAddress());

        CityResponse response = reader.city(ipAddress);
        City city = response.getCity();

        cityNameWithCountryCodeFromGeoLoc = city.getName();
    }

    public String getCityNameWithCountryCodeFromGeoLoc() {
        return cityNameWithCountryCodeFromGeoLoc;
    }

    public String getMyIpAddress() {
        String address = "";
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            address = in.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}


