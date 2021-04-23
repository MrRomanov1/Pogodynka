package pl.piotr_romanczak.controller;

import pl.piotr_romanczak.Pogodynka;
import pl.piotr_romanczak.view.ViewFactory;

public abstract class BaseController {
    public Pogodynka pogodynka;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(Pogodynka pogodynka, ViewFactory viewFactory, String fxmlName) {
        this.pogodynka = pogodynka;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}