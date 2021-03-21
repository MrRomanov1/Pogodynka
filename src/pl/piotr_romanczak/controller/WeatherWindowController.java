package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.view.ViewFactory;

public class WeatherWindowController extends BaseController {

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

    public WeatherWindowController(Pogodynka pogodynka, ViewFactory viewFactory, String fxmlName) {
        super(pogodynka, viewFactory, fxmlName);
    }
}