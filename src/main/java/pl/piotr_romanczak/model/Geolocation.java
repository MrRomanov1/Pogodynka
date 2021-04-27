package pl.piotr_romanczak.model;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import pl.piotr_romanczak.controller.ErrorMessages;
import pl.piotr_romanczak.controller.Statements;

import java.io.*;
import java.net.*;

public class Geolocation {
    private static final String RESOURCE_PATH = "/cities/GeoLite2-City.mmdb";

    public String getCityNameWithCountryCodeFromGeoLoc() throws IOException, GeoIp2Exception {

        InputStream resourceAsStream = getClass().getResourceAsStream(RESOURCE_PATH);
        File file = streamToFile(resourceAsStream);
        DatabaseReader reader = new DatabaseReader.Builder(file).build();
        InetAddress ipAddress = InetAddress.getByName(getMyIpAddress());

        CityResponse response = reader.city(ipAddress);
        City city = response.getCity();

        return city.getName();
    }

    private String getMyIpAddress() {
        String address = "";
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            address = in.readLine();
        } catch (MalformedURLException e) {
            ErrorMessages.setErrorMessage(Statements.CANNOT_CHECK_IP);
            e.printStackTrace();
        } catch (IOException e) {
            ErrorMessages.setErrorMessage(Statements.CANNOT_CHECK_IP);
            e.printStackTrace();
        }
        return address;
    }

    private static File streamToFile(InputStream in) throws IOException {
        final File tempFile = File.createTempFile("GeoLite2-City-temp", ".mmdb");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            in.transferTo(out);
        }
        return tempFile;
    }
}


