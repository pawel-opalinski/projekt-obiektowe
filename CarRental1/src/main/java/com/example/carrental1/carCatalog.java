package com.example.carrental1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class carCatalog {

    @FXML
    private Button logout;
    public void userLogOut(ActionEvent event) throws IOException {
        HelloApplication next = new HelloApplication();
        next.changeScene2("hello-view.fxml");
    }
}
