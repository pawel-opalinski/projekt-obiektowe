package com.example.carrental1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.example.carrental1.HelloApplication.addClient;


public class Rent_window {



    @FXML
    private Button Back_button;
    @FXML
    private Button Value_button;
    @FXML
    private Button Account_button;
    @FXML
    private Button ID_button;
    @FXML
    private Button Rent_button;
    @FXML
    private CheckBox button_oc;
    @FXML
    private Label text_val;
    @FXML
    private TextField text_Marka;
    @FXML
    private TextField text_Model;
    @FXML
    private TextField text_Cena24h;
    @FXML
    private TextField text_Ubezpieczenie24h;
    @FXML
    private TextField text_Name;
    @FXML
    private TextField text_Subname;
    @FXML
    private TextField text_Phone;
    @FXML
    private TextField text_PESEL;
    @FXML
    private TextField text_ID;
    @FXML
    private DatePicker data_first;
    @FXML
    private DatePicker data_last;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;



    public void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            statement = connection.createStatement();





        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Back(ActionEvent event) throws IOException {
        HelloApplication next = new HelloApplication();
        next.changeScene("carCatalog.fxml");
    }

    public void Rent(ActionEvent event) throws IOException {

        LocalDate y1 = data_first.getValue();
        LocalDate y2 = data_last.getValue();
        String pesel = text_PESEL.getText();
        String tablice = text_ID.getText();


        // Zapisz informacje o wypożyczeniu do bazy danych
        saveRentalInfo(y1, y2, pesel, tablice, (int) val());

        Alert b = new Alert(Alert.AlertType.NONE);
        b.setAlertType(Alert.AlertType.INFORMATION);
        b.setContentText("dane zostały dodane pomyślnie!");
        b.show();

    }
    public long val()
    {
        long val= 0;
        long x = 0;
        LocalDate x1 = data_first.getValue();
        LocalDate x2 = data_last.getValue();
        x = ChronoUnit.DAYS.between(x1, x2);
        if (button_oc.isSelected())
        {
            val = ((x+1)*((Integer.parseInt(text_Cena24h.getText())+(Integer.parseInt(text_Ubezpieczenie24h.getText()))+500)));
        }
        else
        {
            val = ((x+1)*(Integer.parseInt(text_Cena24h.getText())+500));
        }
        text_val.setText(String.valueOf(val));
        return val;
    }
    public void Value(ActionEvent event)
    {
        val();
    }

    public void ID_find(ActionEvent event)
    {
        String tab = text_ID.getText();
        try {
            String query = "SELECT * FROM samochod WHERE tablice = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tab);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                int cena24h = resultSet.getInt("cena");
                int ubezpieczenie24h = resultSet.getInt("ubezpieczenie24");
                text_Marka.setText(marka);
                text_Model.setText(model);
                text_Cena24h.setText(String.valueOf(cena24h));
                text_Ubezpieczenie24h.setText(String.valueOf(ubezpieczenie24h));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void Account(ActionEvent event) {
        String name = text_Name.getText();
        String subname = text_Subname.getText();
        String phone = text_Phone.getText();
        String pesel = text_PESEL.getText();

        // Zapisz informacje o wypożyczeniu do bazy danych
       addClient(pesel, name, subname, phone);

        Alert b = new Alert(Alert.AlertType.NONE);
        b.setAlertType(Alert.AlertType.INFORMATION);
        b.setContentText("dane zostały dodane pomyślnie!");
        b.show();
    }

    public void closeConnection() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveRentalInfo(LocalDate fromDate, LocalDate toDate, String pesel, String tablice, int cena) {
        try {
            String sql = "INSERT INTO wypozyczenia (odKiedy, doKiedy, pesel, tablice, cenaOstateczna) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(fromDate)); // Konwersja fromDate na java.sql.Date
            statement.setDate(2, Date.valueOf(toDate)); // Konwersja toDate na java.sql.Date
            statement.setString(3, pesel);
            statement.setString(4, tablice);
            statement.setInt(5, cena);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

