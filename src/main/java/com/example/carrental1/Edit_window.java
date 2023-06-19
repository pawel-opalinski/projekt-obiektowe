package com.example.carrental1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Edit_window
{
    @FXML
    private Button Back_button;
    @FXML
    private Button Find_button;
    @FXML
    private Button Delete_button;
    @FXML
    private Button Edit_button;

    @FXML
    private TextField text_Find;
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

    public void Find(ActionEvent event) throws IOException
    {
        int id = Integer.parseInt(text_Find.getText());
        displayCar(id);
    }

    public void Delete(ActionEvent event) throws IOException{
        int id = Integer.parseInt(text_Find.getText());
        deleteCar(id);
    }

    public void Edit(ActionEvent event) throws  IOException{
        int id = Integer.parseInt(text_Find.getText());
        editCar(id, text_Tablice.getText(), text_Marka.getText(), text_Model.getText(), text_Paliwo.getText(), Integer.parseInt(text_Miejsca.getText()), Integer.parseInt(text_Cena24h.getText()), Integer.parseInt(text_Ubezpieczenie24h.getText()), 1 );
    }


    public static void editCar(int id, String tablice, String marka, String model, String paliwo, int miejsca, int cena, int ubezpieczenie24, int dostepnosc) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            String sql = "UPDATE samochod SET tablice = ?, marka = ?, model = ?, paliwo = ?, miejsca = ?, cena = ?, ubezpieczenie24 = ?, dostepnosc = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tablice);
            statement.setString(2, marka);
            statement.setString(3, model);
            statement.setString(4, paliwo);
            statement.setInt(5, miejsca);
            statement.setInt(6, cena);
            statement.setInt(7, ubezpieczenie24);
            statement.setInt(8, dostepnosc);
            statement.setInt(9, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Samochód został zaktualizowany.");
            } else {
                System.out.println("Nie udało się zaktualizować samochodu.");
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteCar(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            String sql = "DELETE FROM samochod WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Wiersz został usunięty.");
            } else {
                System.out.println("Nie udało się usunąć wiersza.");
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayCar(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            String sql = "SELECT * FROM samochod WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                String tablice = resultSet.getString("tablice");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                String paliwo = resultSet.getString("paliwo");
                int miejsca = resultSet.getInt("miejsca");
                int cena = resultSet.getInt("cena");
                int ubezpieczenie24 = resultSet.getInt("ubezpieczenie24");
                int dostepnosc = resultSet.getInt("dostepnosc");

                text_Tablice.setText(tablice);
                text_Marka.setText(marka);
                text_Model.setText(model);
                text_Paliwo.setText(paliwo);
                text_Miejsca.setText(String.valueOf(miejsca));
                text_Cena24h.setText(String.valueOf(cena));
                text_Ubezpieczenie24h.setText(String.valueOf(ubezpieczenie24));


            } else {
                System.out.println("Nie znaleziono wiersza o podanym ID.");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





