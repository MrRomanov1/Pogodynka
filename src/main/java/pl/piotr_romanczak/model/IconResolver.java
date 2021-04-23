package pl.piotr_romanczak.model;

import javafx.scene.image.Image;

public class IconResolver {
    private Image image;


    public IconResolver(String iconCode) {
        setImage(iconCode);
    }

    public void setImage(String iconCode) {
        String URLString = "http://openweathermap.org/img/wn/" + iconCode + "@2x.png";
        image = new Image(URLString, 100, 100, false, false);
    }

    public Image getImage() {
        return image;
    }
}
