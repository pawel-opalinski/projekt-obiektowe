package com.example.carrental1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("P&A Cars");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("carCatalog.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene2 = new Scene(fxmlLoader.load(), 845,625);
        stg.setScene(scene2);
        stg.getScene().setRoot(pane);
    }

    public void changeScene2(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("carCatalog.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene2 = new Scene(fxmlLoader.load(), 600,400);
        stg.setScene(scene2);
        stg.getScene().setRoot(pane);
    }
    public static void main(String[] args) {
        launch();
    }
}