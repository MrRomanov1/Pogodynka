package pl.piotr_romanczak.model;

import javafx.scene.image.Image;

public class IconResolver {
    public static Image getImageFromIconCode(String iconCode) {

        String URLString = "http://openweathermap.org/img/wn/" + iconCode + "@2x.png";
        Image image = new Image(URLString, 100, 100, false, false);
        return image;
    }
}
