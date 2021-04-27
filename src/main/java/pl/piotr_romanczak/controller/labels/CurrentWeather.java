package pl.piotr_romanczak.controller.labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import pl.piotr_romanczak.StringUtils;
import pl.piotr_romanczak.controller.Statements;
import pl.piotr_romanczak.model.DateConverter;
import pl.piotr_romanczak.model.WeatherData;

public class CurrentWeather {
    public static final String SVG_ARROW_CONTENT = "M15,6H8V3c0-0.484-0.375-1-1-1C6.539,2,6.305,2.287,6,2.54L0.625,7C0.242,7.313,0,7.555," +
            "0,8s0.242,0.688,0.625,1L6,13.46  C6.305,13.713,6.539,14,7,14c0.625,0,1-0.516,1-1v-3h7c0.55,0,1-0.45,1-"
            + "1V7C16,6.45,15.55,6,15,6z";
    public static void setCurrentWeatherLabels(VBox weatherVbox, WeatherData weatherData) {

        weatherVbox.setAlignment(Pos.TOP_CENTER);

        Label temperatureLabel = new Label(String.format("%3.1f", weatherData.getCurrent().getTemp()) + "°C");
        temperatureLabel.getStyleClass().add("currentTemp");

        Label descriptionLabel = new Label(StringUtils.capitalize(weatherData.getCurrent().getWeather().
                get(0).getDescription()));

        Label updateTimeLabel = new Label(Statements.LAST_UPDATED_ON_PREFIX + DateConverter.dateConverter(weatherData.
                getCurrent().getDt()));

        weatherVbox.getChildren().add(temperatureLabel);
        weatherVbox.getChildren().add(descriptionLabel);
        weatherVbox.getChildren().add(updateTimeLabel);
    }

    public static void setCurrentWeatherFirstBox(HBox firstBox, WeatherData weatherData) {

        Label feelsLikeLabel = new Label(Statements.FEELS_LIKE_TEMP_PREFIX+ String.format("%3.1f",
                weatherData.getCurrent().getFeels_like()) + "°C");

        Label windLabel = new Label(Statements.WIND_PREFIX + String.format("%4.1f",
                weatherData.getCurrent().getWind_speed()) + " m/s");
        SVGPath windDirection = new SVGPath();
        windDirection.setContent(SVG_ARROW_CONTENT);
        windDirection.setFill(Color.WHITE);
        windDirection.setRotate(weatherData.getCurrent().getWind_deg() - 90);
        windLabel.setGraphic(windDirection);
        Label visibilityLabel = new Label(Statements.VISIBILITY_PREFIX + weatherData.getCurrent().getVisibility() / 1000 + " km");

        firstBox.getChildren().add(feelsLikeLabel);
        firstBox.getChildren().add(windLabel);
        firstBox.getChildren().add(visibilityLabel);
    }

    public static void setCurrentWeatherSecondBox(HBox secondBox, WeatherData weatherData) {
        Label pressureLabel = new Label(Statements.PRESSURE_PREFIX + weatherData.getCurrent().getPressure() + " hPa");
        Label humidity = new Label(Statements.HUMIDITY_PREFIX + weatherData.getCurrent().getHumidity() + "%");
        Label dewPoint = new Label (Statements.DEW_POINT_PREFIX + String.format("%3.1f", weatherData.getCurrent().
                getDew_point()) + "°C");

        secondBox.getChildren().add(pressureLabel);
        secondBox.getChildren().add(humidity);
        secondBox.getChildren().add(dewPoint);
    }
}
