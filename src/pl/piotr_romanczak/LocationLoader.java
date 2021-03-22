package pl.piotr_romanczak;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.piotr_romanczak.model.LocationData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LocationLoader {

    private List<LocationData> locationList;
    public static final String RESOURCE_NAME = "src\\pl\\piotr_romanczak\\resources\\city.list.json";

    public LocationLoader() throws IOException {
        loadLocationListFromJSON();
    }

    public List<LocationData> getCitiesList() {
        return locationList;
    }

    private void loadLocationListFromJSON() {

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Path.of(RESOURCE_NAME));
            locationList = gson.fromJson(reader, new TypeToken<List<LocationData>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
