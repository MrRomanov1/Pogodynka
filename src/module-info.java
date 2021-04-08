module Pogodynka {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires geoip2;

    opens pl.piotr_romanczak;
    opens pl.piotr_romanczak.view;
    opens pl.piotr_romanczak.controller;
    opens pl.piotr_romanczak.model;
}