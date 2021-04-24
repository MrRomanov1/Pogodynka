package pl.piotr_romanczak.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LocationLoader {

    private List<LocationData> locationList;
    public static final String RESOURCE_PATH = "/json/city.list.min.json";

    public LocationLoader() throws IOException {
        loadLocationListFromJSON();
    }

    public List<LocationData> getCitiesList() {
        return locationList;
    }

    private void loadLocationListFromJSON() {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream(RESOURCE_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
            Gson gson = new Gson();
            locationList = gson.fromJson(reader, new TypeToken<List<LocationData>>() {
            }.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}