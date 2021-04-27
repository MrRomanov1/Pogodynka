package pl.piotr_romanczak.controller.labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.piotr_romanczak.model.DateConverter;
import pl.piotr_romanczak.model.IconResolver;
import pl.piotr_romanczak.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class DailyWeather {

    public static void setDailyWeatherLabels(WeatherData weatherData, ScrollPane dailyWeatherContainer) {

        dailyWeatherContainer.setPannable(true);
        HBox weatherHBox = new HBox();
        weatherHBox.setSpacing(10);
        dailyWeatherContainer.setContent(weatherHBox);
        List<VBox> dailyWeatherBoxes = new ArrayList<>();

        for (int i = 0; i < weatherData.getDaily().size(); i++) {
            dailyWeatherBoxes.add(new VBox());
            weatherHBox.getChildren().add(dailyWeatherBoxes.get(i));
            dailyWeatherBoxes.get(i).setAlignment(Pos.CENTER);
            dailyWeatherBoxes.get(i).setMinWidth(200);
            dailyWeatherBoxes.get(i).setStyle("-fx-background-color: rgba(40, 112, 175, 0.2); " +
                    "-fx-border-radius: 20, 20, 20, 20; -fx-background-radius: 20 20 20 20;");
            dailyWeatherBoxes.get(i).getChildren().add(new Label(DateConverter.getDayName(weatherData.
                    getDaily().get(i).getDt())));
            IconResolver iconResolver = new IconResolver(weatherData.getDaily().get(i).getWeather().get(0).getIcon());
            dailyWeatherBoxes.get(i).getChildren().add(new ImageView(iconResolver.getImage()));
            dailyWeatherBoxes.get(i).getChildren().add(new Label(String.format("%3.1f", weatherData.getDaily().
                    get(i).getTemp().getMax()) + "°C"));
            dailyWeatherBoxes.get(i).getChildren().add(new Label(weatherData.getDaily().get(i).getWeather().
                    get(0).getDescription()));
        }
    }
}
