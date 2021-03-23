package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import pl.piotr_romanczak.LocationLoader;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.model.LocationData;
import pl.piotr_romanczak.model.WeatherData;
import pl.piotr_romanczak.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class WeatherWindowController extends BaseController implements Initializable {

    @FXML
    private TextField firstCityLabel;

    @FXML
    private TextField secondCityLabel;

    @FXML
    private Label firstCityTemperatureLabel;

    @FXML
    private Label secondCityTemperatureLabel;

    @FXML
    private Label firstCityWeatherLabel;

    @FXML
    private Label secondCityWeatherLabel;

    private List<LocationData> citiesList;
    private HashMap<String, String> cityNames;
    public WeatherData weatherData = new WeatherData();

    public void getCityListWithCountryCodes() throws IOException {
        citiesList = new LocationLoader().getCitiesList();
        cityNames = new HashMap<>();

        for (LocationData locationData : citiesList) {
            String city = locationData.getCityName();
            String countryCode = locationData.getCountryCode();
            cityNames.put(city, city + ", " + countryCode);
        }
    }


    public WeatherWindowController(Pogodynka pogodynka, ViewFactory viewFactory, String fxmlName) {
        super(pogodynka, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getCityListWithCountryCodes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        autocompleteTextField(firstCityLabel);

        firstCityLabel.textProperty().addListener(e -> {
            getWeatherData(firstCityLabel.getText());
        });
        autocompleteTextField(secondCityLabel);

        secondCityLabel.textProperty().addListener(e -> {
            getWeatherData(secondCityLabel.getText());
        });

    }

    private int getCityId(String cityName) {
        int cityId = 0;

        for (int i = 0; i < citiesList.size(); i++) {
            if (citiesList.get(i).getCityName().equals(cityName)) {
                cityId = citiesList.get(i).getId();
                System.out.println(cityId);
                return cityId;
            }
        }
        return cityId;
    }

    public void autocompleteTextField(TextField textField) {
        TextFields.bindAutoCompletion(textField, cityNames.values());
    }

    public void getWeatherData(String cityLabel) {
        if (cityNames.containsValue(cityLabel)) {
            try {
                String[] cityName = cityLabel.split(",");
                int cityId = getCityId(cityName[0]);
                weatherData.getWeatherData(cityId);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}