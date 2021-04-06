package pl.piotr_romanczak.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import org.controlsfx.control.textfield.TextFields;
import pl.piotr_romanczak.AdditionalMethods;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.model.*;
import pl.piotr_romanczak.view.ViewFactory;

import java.net.URL;
import java.util.*;

public class WeatherWindowController extends BaseController implements Initializable {

    @FXML
    private GridPane weatherContainer;

    @FXML
    private TextField firstCityLabel;

    @FXML
    private Label firstCityTemperatureLabel;

    @FXML
    private Label firstCityWeatherLabel;

    @FXML
    private Label firstCityUpdateTime;

    @FXML
    private Label firstCityFeelsLikeTemp;

    @FXML
    private Label firstCityWind;

    @FXML
    private Label firstCityVisibility;

    @FXML
    private TextField secondCityLabel;

    @FXML
    private Label secondCityTemperatureLabel;

    @FXML
    private Label secondCityWeatherLabel;

    @FXML
    private Label secondCityUpdateTime;

    @FXML
    private Label secondCityFeelsLikeTemp;

    @FXML
    private Label secondCityWind;

    @FXML
    private Label secondCityVisibility;

    @FXML
    private SVGPath windDirection;

    @FXML
    private Label firstCityPressure;

    @FXML
    private Label firstCityHumidity;

    @FXML
    private Label firstCityDewPoint;

    private final List<LocationData> citiesList = pogodynka.getCitiesList();
    private final HashMap<String, String> cityNames = pogodynka.getCityNames();
    public WeatherData weatherData;

    public WeatherWindowController(Pogodynka pogodynka, ViewFactory viewFactory, String fxmlName) {
        super(pogodynka, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        autocompleteTextField(firstCityLabel);

        firstCityLabel.textProperty().addListener(e -> {
            getWeatherData(firstCityLabel.getText(), "first");
        });
        autocompleteTextField(secondCityLabel);

        secondCityLabel.textProperty().addListener(e -> {
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
                    setFirstCurrentWeatherLabels(weatherData);
                    setFirstDailyWeatherLabels(weatherData);
                    break;
                }
                case "second": {
                    //setSecondWeatherLabels(weatherData);
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + window);
            }

        } else {
            //code goes here
        }
    }

    private void setFirstCurrentWeatherLabels(WeatherData weatherData) {
        firstCityTemperatureLabel.setText(String.format("%3.1f", weatherData.getCurrent().getTemp()) + "°C");
        firstCityWeatherLabel.setText(AdditionalMethods.capitalize(weatherData.getCurrent().getWeather().get(0).getDescription()));
        firstCityUpdateTime.setText("Zaktualizowano o godzinie: " + DateConverter.dateConverter(weatherData.getCurrent().getDt()));
        firstCityFeelsLikeTemp.setText("Temperatura odczuwalna: " + String.format("%3.1f", weatherData.getCurrent().getFeels_like()) + "°C");
        firstCityWind.setText("Wiatr: " + String.format("%4.1f", weatherData.getCurrent().getWind_speed()) + " m/s");
        firstCityVisibility.setText("Widoczność: " + String.valueOf(weatherData.getCurrent().getVisibility() / 1000) + " km");
        windDirection.setRotate(weatherData.getCurrent().getWind_deg() - 90);
        firstCityPressure.setText("Ciśnienie: " + weatherData.getCurrent().getPressure() + " hPa");
        firstCityHumidity.setText("Wilgotność powietrza: " + weatherData.getCurrent().getHumidity() + "%");
        firstCityDewPoint.setText("Temperatura punktu rosy: " + weatherData.getCurrent().getDew_point() + "°C");
    }

    private void setFirstDailyWeatherLabels(WeatherData weatherData) {
        ScrollPane weatherScrollPane = new ScrollPane();
        weatherScrollPane.setPannable(true);
        weatherContainer.add(weatherScrollPane, 0,3);
        HBox weatherHBox = new HBox();
        weatherScrollPane.setContent(weatherHBox);
        VBox dailyWeatherBoxes[] = new VBox[weatherData.getDaily().size()];
        for (int i = 0; i < weatherData.getDaily().size(); i++) {
            dailyWeatherBoxes[i] = new VBox();
            weatherHBox.getChildren().add(dailyWeatherBoxes[i]);
            dailyWeatherBoxes[i].setAlignment(Pos.CENTER);
            dailyWeatherBoxes[i].getChildren().add(new Label(DateConverter.getDayName(weatherData.getDaily().get(i).getDt())));
            dailyWeatherBoxes[i].getChildren().add(new ImageView(IconResolver.getImageFromIconCode(weatherData.getDaily().get(i).getWeather().get(0).getIcon())));
            dailyWeatherBoxes[i].getChildren().add(new Label (String.format("%3.1f", weatherData.getDaily().get(i).getTemp().getMax()) + "°C"));
            dailyWeatherBoxes[i].getChildren().add(new Label(weatherData.getDaily().get(i).getWeather().get(0).getDescription()));

        }
    }
}