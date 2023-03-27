package com.example.carrental1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class carValuation {

    @FXML
    private Button cancel;

    public void backTo(ActionEvent event) throws IOException {
        HelloApplication next = new HelloApplication();
        next.changeScene("carCatalog.fxml");
    }
}
