package pl.piotr_romanczak.model;

public class BackgroundSetter {
    private String imageResource;

    public BackgroundSetter(int weatherCode, long currentTime, long sunsetTime) {
        setAppBackground(weatherCode, currentTime, sunsetTime);
    }

    public void setAppBackground(int weatherCode, long currentTime, long sunsetTime) {
        if (weatherCode >= 200 && weatherCode < 300) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\thunderstorm.jpg";
        } else if (weatherCode >= 300 && weatherCode < 600) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\rain.jpg";
        } else if (weatherCode >= 600 && weatherCode < 700) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\snow.jpg";
        } else if (weatherCode >= 700 && weatherCode < 800) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\mist.jpg";
        } else if (weatherCode == 800 && sunsetTime > currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\clear-sky-day.jpg";
        } else if (weatherCode == 800 && sunsetTime < currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\clear-sky-night.jpg";
        } else if (weatherCode == 801 && sunsetTime > currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\clouds-few-day.jpg";
        } else if (weatherCode == 801 && sunsetTime < currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\clouds-few-night.jpg";
        } else if (weatherCode == 802 && sunsetTime > currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\scattered-clouds-day.jpg";
        } else if (weatherCode == 802 && sunsetTime < currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\scattered-clouds-night.jpg";
        } else if (weatherCode == 803 && sunsetTime > currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\broken-clouds-day.jpg";
        } else if (weatherCode == 803 && sunsetTime < currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\broken-clouds-night.jpg";
        } else if (weatherCode == 804 && sunsetTime > currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\overcast-clouds-day.png";
        } else if (weatherCode == 804 && sunsetTime < currentTime) {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\overcast-clouds-night.png";
        } else {
            imageResource = "file:src\\pl\\piotr_romanczak\\resources\\img\\mist.jpg";
        }
    }

    public String getImageResource() {
        return imageResource;
    }
}
