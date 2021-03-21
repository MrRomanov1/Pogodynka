package pl.piotr_romanczak.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.controller.BaseController;
import pl.piotr_romanczak.controller.WeatherWindowController;

import java.io.IOException;

public class ViewFactory {

    private Pogodynka pogodynka;

    public ViewFactory(Pogodynka pogodynka) {
        this.pogodynka = pogodynka;
    }

    public void showMainWindow() {
        BaseController controller = new WeatherWindowController(pogodynka, this, "WeatherWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
