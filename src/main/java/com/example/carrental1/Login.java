package com.example.carrental1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Login {

    public Login(){

    }
    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();

    }

    public void checkLogin() throws IOException {
        HelloApplication next = new HelloApplication();
        if((username.getText().toString().equals("JP") && password.getText().toString().equals("JPJP")) || (username.getText().toString().equals("PO") && password.getText().toString().equals("POPO")))
        {
            wrongLogin.setText("Sukces!");

            next.changeScene("carCatalog.fxml");
        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Proszę, wpisz Login i Hasło");
        }

        else {
            wrongLogin.setText("Złe hasło/login");
        }
    }

}
