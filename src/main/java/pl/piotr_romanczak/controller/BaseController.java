package pl.piotr_romanczak.controller;

import pl.piotr_romanczak.view.ViewFactory;

public abstract class BaseController {

    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}