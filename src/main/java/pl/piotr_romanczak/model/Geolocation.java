package pl.piotr_romanczak.model;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;

import java.io.*;
import java.net.*;

public class Geolocation {
    private String cityNameWithCountryCodeFromGeoLoc;
    private static final String RESOURCE_PATH = "/cities/GeoLite2-City.mmdb";

    public Geolocation() throws IOException, GeoIp2Exception {
        setCityWithCountryCodeFromGeoLoc();
    }

    public void setCityWithCountryCodeFromGeoLoc() throws IOException, GeoIp2Exception {

        InputStream resourceAsStream = getClass().getResourceAsStream(RESOURCE_PATH);
        File file = streamToFile(resourceAsStream);
        DatabaseReader reader = new DatabaseReader.Builder(file).build();
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

    public static File streamToFile(InputStream in) throws IOException {
        final File tempFile = File.createTempFile("GeoLite2-City-temp", ".mmdb");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            in.transferTo(out);
        }
        return tempFile;
    }
}


