package com.example.carrental1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import static com.example.carrental1.HelloApplication.addCar;

public class Add_window
{

    @FXML
    private Button Back_button;
    @FXML
    private Button Add_button;

    @FXML
    private TextField text_Tablice;
    @FXML
    private TextField text_Model;
    @FXML
    private TextField text_Marka;
    @FXML
    private TextField text_Paliwo;
    @FXML
    private TextField text_Miejsca;
    @FXML
    private TextField text_Cena24h;
    @FXML
    private TextField text_Ubezpieczenie24h;
    public void Back(ActionEvent event) throws IOException
    {
        HelloApplication next = new HelloApplication();
        next.changeScene("carCatalog.fxml");
    }


    public void Add(ActionEvent event) throws IOException
    {
        Cars_spec car = new Cars_spec();
        car.Tablice = text_Tablice.getText();
        car.Marka = text_Marka.getText();
        car.Model = text_Model.getText();
        car.Paliwo = text_Paliwo.getText();
        car.Miejsca = Integer.parseInt(text_Miejsca.getText());
        car.Cena24h = Integer.parseInt(text_Cena24h.getText());
        car.Ubezpieczenie24h = Integer.parseInt(text_Ubezpieczenie24h.getText());

        addCar(car.Tablice, car.Marka, car.Model, car.Paliwo, car.Miejsca, car.Cena24h, car.Ubezpieczenie24h,1);





        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.INFORMATION);
        a.setContentText("Auto dodane pomyślnie.");
        a.show();

        HelloApplication next = new HelloApplication();
        next.changeScene("carCatalog.fxml");

    }
}
