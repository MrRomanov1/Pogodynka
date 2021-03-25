package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import pl.piotr_romanczak.model.LocationLoader;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.model.LocationData;
import pl.piotr_romanczak.model.WeatherData;
import pl.piotr_romanczak.model.WeatherQuerry;
import pl.piotr_romanczak.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private WeatherData weatherData;

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

    private List<Double> getCityParams(String cityName) {
        List<Double> cityParams = new ArrayList<>();

        for (int i = 0; i < citiesList.size(); i++) {
            if (citiesList.get(i).getCityName().equals(cityName)) {
                cityParams.add(citiesList.get(i).getCoord().getLat());
                cityParams.add(citiesList.get(i).getCoord().getLon());

                return cityParams;
            }
        }
        return cityParams;
    }

    public void autocompleteTextField(TextField textField) {
        TextFields.bindAutoCompletion(textField, cityNames.values());
    }

    public void getWeatherData(String cityLabel) {
        if (cityNames.containsValue(cityLabel)) {
            String[] cityName = cityLabel.split(",");
            List<Double> cityParams = getCityParams(cityName[0]);
            weatherData = new WeatherQuerry(cityParams).getWeatherData();
            System.out.println(weatherData.getCurrent().getPressure());
        }
    }
}