package com.example.carrental1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class HelloApplication extends Application {

    private static String selectedCarTablice; // Pole przechowujące wybraną tablicę samochodu


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
        connect();
    }


    public static void connect(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM samochod");

            while (resultSet.next()) {
                String tablice = resultSet.getString("tablice");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                String paliwo = resultSet.getString("paliwo");
                int miejsca = resultSet.getInt("miejsca");
                int cena = resultSet.getInt("cena");
                int ubezpieczenie24 = resultSet.getInt("ubezpieczenie24");
                int dostepnosc = resultSet.getInt("dostepnosc");

                Samochod samochod = new Samochod(tablice, marka, model, paliwo, miejsca, cena, ubezpieczenie24, dostepnosc);

                System.out.println(samochod.toString());
                List<String> listaObiektow = new ArrayList<>();
                listaObiektow.add(String.valueOf(samochod));
                System.out.println(listaObiektow);




                // Przetwarzanie obiektu samochodu...
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void addClient(String pesel, String imie, String nazwisko, String numer_telefonu){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            String sql = "INSERT INTO klienci (pesel, imie, nazwisko, numerTelefony) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pesel);
            statement.setString(2, imie);
            statement.setString(3, nazwisko);
            statement.setString(4, numer_telefonu);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nowy wiersz został dodany.");
            } else {
                System.out.println("Nie udało się dodać nowego wiersza");
            }

            statement.close();
            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void addCar(String tablice, String marka, String model, String paliwo, int miejsca, int cena, int ubezpieczenie24, int dostepnosc){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            String sql = "INSERT INTO samochod (tablice, marka, model, paliwo, miejsca, cena, ubezpieczenie24, dostepnosc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tablice);
            statement.setString(2, marka);
            statement.setString(3, model);
            statement.setString(4, paliwo);
            statement.setInt(5, miejsca);
            statement.setInt(6, cena);
            statement.setInt(7,ubezpieczenie24);
            statement.setInt(8, dostepnosc);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nowy wiersz został dodany.");
            } else {
                System.out.println("Nie udało się dodać nowego wiersza");
            }

            statement.close();
            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }




    public void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("carCatalog.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene2 = new Scene(fxmlLoader.load(), 800,600);
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


    public void Add_scene(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Add_window.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene_add = new Scene(fxmlLoader.load(), 800,600);
        stg.setScene(scene_add);
        stg.getScene().setRoot(pane);
    }





    public void Edit_scene(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Edit_window.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene_edit = new Scene(fxmlLoader.load(), 800,600);
        stg.setScene(scene_edit);
        stg.getScene().setRoot(pane);
    }

    public void Rent_scene(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Rent_window.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene_rent = new Scene(fxmlLoader.load(), 800,600);
        stg.setScene(scene_rent);
        stg.getScene().setRoot(pane);
    }









    public static void main(String[] args) {
        launch();
    }
}