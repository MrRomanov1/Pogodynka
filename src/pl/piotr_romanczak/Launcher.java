package pl.piotr_romanczak;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.piotr_romanczak.view.ViewFactory;

import java.io.IOException;


public class Launcher extends Application {

    private Pogodynka pogodynka = new Pogodynka();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory viewFactory = new ViewFactory(pogodynka);
        viewFactory.showMainWindow();
    }
}