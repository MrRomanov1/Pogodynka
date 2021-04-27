package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.controlsfx.control.textfield.TextFields;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.StringUtils;
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

    private final List<LocationData> citiesList;
    private final Map<String, String> cityNames;
    private final Map<String, String> cityNamesWithoutPolishCharacters;
    private final String cityNameWithCountryCodeFromGeoLoc;
    private Pogodynka pogodynka;
    private WeatherData weatherData;

    public WeatherWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        pogodynka = new Pogodynka();
        citiesList = pogodynka.getCitiesList();
        cityNames = pogodynka.getCityNames();
        cityNamesWithoutPolishCharacters = StringUtils.setCityNamesWithoutPolishCharacters(pogodynka.getCityNames());
        cityNameWithCountryCodeFromGeoLoc = pogodynka.getCityNameWithCountryCodeFromGeoLoc();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!geolocationCityNameEqualsWeatherDataCityName()) {
            firstCityLabel.setText("Warszawa, PL");
        }
        getWeatherData(firstCityLabel.getText(), "first");
        autocompleteTextField(firstCityLabel);

        firstCityLabel.textProperty().
                addListener(e -> getWeatherData(firstCityLabel.getText(), "first"));
        secondCityLabel.setText("Warszawa, PL");
        if (!secondCityLabel.getText().isEmpty()) {
            getWeatherData(secondCityLabel.getText(), "second");
        }

        autocompleteTextField(secondCityLabel);

        secondCityLabel.textProperty().
                addListener(e -> getWeatherData(secondCityLabel.getText(), "second"));
    }

    private List<Double> getCityParams(String cityName) {
        List<Double> cityParams = new ArrayList<>();

        for (LocationData locationData : citiesList) {
            if (locationData.getCityName().equals(cityName)) {
                cityParams.add(locationData.getCoord().getLat());
                cityParams.add(locationData.getCoord().getLon());
            }
        }
        if (cityParams.isEmpty()) {
            ErrorMessages.setErrorMessage(Statements.CITY_NOT_FOUND + cityName);
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
            weatherData = new WeatherQuery(cityParams).getWeatherData();
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
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        weatherContainer.setBackground(new Background(backgroundImage));
        weatherBox.getChildren().removeAll(weatherBox.getChildren());
        firstBox.getChildren().clear();
        secondBox.getChildren().clear();
        CurrentWeather.setCurrentWeatherLabels(weatherBox, weatherData);
        CurrentWeather.setCurrentWeatherFirstBox(firstBox, weatherData);
        CurrentWeather.setCurrentWeatherSecondBox(secondBox, weatherData);
        if (ErrorMessages.getErrorMessage() != null) {
            firstBox.getChildren().add(new Label(ErrorMessages.getErrorMessage()));
        }
    }

    private boolean geolocationCityNameEqualsWeatherDataCityName() {
        for (Map.Entry<String, String> entry : cityNamesWithoutPolishCharacters.entrySet()) {
            if (entry.getValue().equals(cityNameWithCountryCodeFromGeoLoc)) {
                firstCityLabel.setText(cityNames.get(entry.getKey()));
                return true;
            }
        }
        return false;
    }
}