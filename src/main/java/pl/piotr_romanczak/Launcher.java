package pl.piotr_romanczak;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.piotr_romanczak.view.ViewFactory;

public class Launcher extends Application {

    private Pogodynka pogodynka = new Pogodynka();

    public static void runApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(pogodynka);
        viewFactory.showMainWindow();
    }
}