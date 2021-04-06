package pl.piotr_romanczak.controller.labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.piotr_romanczak.model.DateConverter;
import pl.piotr_romanczak.IconResolver;
import pl.piotr_romanczak.model.WeatherData;

public class DailyWeather {

    public static void setDailyWeatherLabels(WeatherData weatherData, ScrollPane dailyWeatherContainer) {

        dailyWeatherContainer.setPannable(true);
        HBox weatherHBox = new HBox();
        dailyWeatherContainer.setContent(weatherHBox);
        VBox dailyWeatherBoxes[] = new VBox[weatherData.getDaily().size()];
        for (int i = 0; i < weatherData.getDaily().size(); i++) {
            dailyWeatherBoxes[i] = new VBox();
            weatherHBox.getChildren().add(dailyWeatherBoxes[i]);
            dailyWeatherBoxes[i].setAlignment(Pos.CENTER);
            dailyWeatherBoxes[i].getChildren().add(new Label(DateConverter.getDayName(weatherData.getDaily().get(i).getDt())));
            dailyWeatherBoxes[i].getChildren().add(new ImageView(IconResolver.getImageFromIconCode(weatherData.getDaily().get(i).getWeather().get(0).getIcon())));
            dailyWeatherBoxes[i].getChildren().add(new Label(String.format("%3.1f", weatherData.getDaily().get(i).getTemp().getMax()) + "°C"));
            dailyWeatherBoxes[i].getChildren().add(new Label(weatherData.getDaily().get(i).getWeather().get(0).getDescription()));
        }
    }
}
