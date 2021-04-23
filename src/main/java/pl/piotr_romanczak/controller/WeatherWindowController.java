package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.controlsfx.control.textfield.TextFields;
import pl.piotr_romanczak.AdditionalMethods;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.controller.labels.CurrentWeather;
import pl.piotr_romanczak.controller.labels.DailyWeather;
import pl.piotr_romanczak.model.*;
import pl.piotr_romanczak.view.ViewFactory;

import java.net.URL;
import java.util.*;

public class WeatherWindowController extends BaseController implements Initializable {

    @FXML
    private GridPane firstWeatherContainer;

    @FXML
    private HBox firstCityFirstBox;

    @FXML
    private TextField firstCityLabel;

    @FXML
    private VBox firstCityWeatherBox;

    @FXML
    private ScrollPane firstDailyWeatherContainer;

    @FXML
    private HBox firstCitySecondBox;

    @FXML
    private GridPane secondWeatherContainer;

    @FXML
    private HBox secondCityFirstBox;

    @FXML
    private TextField secondCityLabel;

    @FXML
    private VBox secondCityWeatherBox;

    @FXML
    private ScrollPane secondDailyWeatherContainer;

    @FXML
    private HBox secondCitySecondBox;


    private final List<LocationData> citiesList = pogodynka.getCitiesList();
    private final HashMap<String, String> cityNames = pogodynka.getCityNames();
    private final HashMap<String, String> cityNamesWithoutPolishCharacters =
            AdditionalMethods.setCityNamesWithoutPolishCharacters(pogodynka.getCityNames());
    private final String cityNameWithCountryCodeFromGeoLoc = pogodynka.getCityNameWithCountryCodeFromGeoLoc();
    public WeatherData weatherData;

    public WeatherWindowController(Pogodynka pogodynka, ViewFactory viewFactory, String fxmlName) {
        super(pogodynka, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!geolocationEqualsWeatherData()) {
            firstCityLabel.setText("Warszawa, PL");
            getWeatherData(firstCityLabel.getText(), "first");
        } else if (!firstCityLabel.getText().isEmpty()) {
            getWeatherData(firstCityLabel.getText(), "first");
        }
        autocompleteTextField(firstCityLabel);

        firstCityLabel.textProperty().
                addListener(e ->
                {
                    getWeatherData(firstCityLabel.getText(), "first");
                });
        secondCityLabel.setText("Warszawa, PL");
        if (!secondCityLabel.getText().isEmpty()) {
            getWeatherData(secondCityLabel.getText(), "second");
        }

        autocompleteTextField(secondCityLabel);

        secondCityLabel.textProperty().
                addListener(e ->
                {
                    getWeatherData(secondCityLabel.getText(), "second");
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

    public void getWeatherData(String cityLabel, String window) {
        if (cityNames.containsValue(cityLabel)) {
            String[] cityName = cityLabel.split(",");
            List<Double> cityParams = getCityParams(cityName[0]);
            weatherData = new WeatherQuerry(cityParams).getWeatherData();
            switch (window) {
                case "first": {
                    setCurrentWeatherLabels(weatherData, firstCityWeatherBox, firstCityFirstBox, firstCitySecondBox,
                            firstWeatherContainer);
                    DailyWeather.setDailyWeatherLabels(weatherData, firstDailyWeatherContainer);
                    break;
                }
                case "second": {
                    setCurrentWeatherLabels(weatherData, secondCityWeatherBox, secondCityFirstBox, secondCitySecondBox,
                            secondWeatherContainer);
                    DailyWeather.setDailyWeatherLabels(weatherData, secondDailyWeatherContainer);
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + window);
            }
        }
    }

    private void setCurrentWeatherLabels(WeatherData weatherData, VBox weatherBox, HBox firstBox, HBox secondBox,
                                         GridPane weatherContainer) {

        BackgroundSetter backgroundSetter = new BackgroundSetter(weatherData.getCurrent().getWeather().get(0).getId(),
                weatherData.getCurrent().getDt(), weatherData.getCurrent().getSunset());
        BackgroundImage backgroundImage = new BackgroundImage(new Image(backgroundSetter.getImageResource()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,true, true, true, true));
        weatherContainer.setBackground(new Background(backgroundImage));
        weatherBox.getChildren().removeAll(weatherBox.getChildren());
        firstBox.getChildren().clear();
        secondBox.getChildren().clear();
        CurrentWeather.setCurrentWeatherLabels(weatherBox, weatherData);
        CurrentWeather.setCurrentWeatherFirstBox(firstBox, weatherData);
        CurrentWeather.setCurrentWeatherSecondBox(secondBox, weatherData);
    }

    public boolean geolocationEqualsWeatherData() {
        for (Map.Entry<String, String> entry : cityNamesWithoutPolishCharacters.entrySet()) {
            if (entry.getValue().equals(cityNameWithCountryCodeFromGeoLoc)) {
                firstCityLabel.setText(cityNames.get(entry.getKey()));
                return true;
            }
        }
        return false;
    }
}