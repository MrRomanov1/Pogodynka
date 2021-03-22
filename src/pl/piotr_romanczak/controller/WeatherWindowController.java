package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import pl.piotr_romanczak.LocationLoader;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.model.LocationData;
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

        firstCityLabel.textProperty().addListener(e -> {
            if (!firstCityLabel.getText().isEmpty()) {
                autocompleteTextField(firstCityLabel);
            }
        });
        secondCityLabel.textProperty().addListener(e -> {
            if (!secondCityLabel.getText().isEmpty()) {
                autocompleteTextField(secondCityLabel);
            }
        });
    }

    public void autocompleteTextField(TextField textField) {
        TextFields.bindAutoCompletion(textField, cityNames.values());
    }
}