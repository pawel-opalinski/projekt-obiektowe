package com.example.carrental1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.Alert.AlertType;


import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.sql.*;



public class carCatalog {
    @FXML
    private Button logout;
    @FXML
    private Button Add_button;
    @FXML
    private Button Edit_button;
    @FXML
    private Button Rent_button;
    @FXML
    private Button Filtruj;
    @FXML
    private ListView<String> carListView;




    @FXML
    private CheckBox check_marka1;
    @FXML
    private CheckBox check_marka2;
    @FXML
    private CheckBox check_marka3;
    @FXML
    private CheckBox check_marka4;
    @FXML
    private CheckBox check_marka5;
    @FXML
    private CheckBox check_marka6;
    @FXML
    private CheckBox check_paliwo1;
    @FXML
    private CheckBox check_paliwo2;
    @FXML
    private CheckBox check_paliwo3;
    @FXML
    private CheckBox check_paliwo4;
    @FXML
    private CheckBox check_miejsca1;
    @FXML
    private CheckBox check_miejsca2;
    @FXML
    private CheckBox check_miejsca3;
    @FXML
    private CheckBox check_miejsca4;
    @FXML
    private TextField text_cena_low;
    @FXML
    private TextField text_cena_high;
    @FXML
    private TextField text_ubez_low;
    @FXML
    private TextField text_ubez_high;
    @FXML
    private DatePicker data_low;
    @FXML
    private DatePicker data_high;












    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public String selectedCarTablice; // Przechowuje tablice wybranego samochodu
    public String selectedCarMarka; // Przechowuje marke wybranego samochodu
    public String selectedCarModel; // Przechowuje tablice wybranego samochodu
    public int selectedCarCena; // Przechowuje tablice wybranego samochodu
    public int selectedCarUbezpieczenie; // Przechowuje tablice wybranego samochodu

    public void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/obiektowe", "root", "P@blo2001");
            statement = connection.createStatement();

            refreshCarList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getSelectedMarksClause() {
        String clause = "";
        if (check_marka1.isSelected()) {
            clause += "marka = 'BMW' OR ";
        }
        if (check_marka2.isSelected())
        {
            clause += "marka = 'VW' OR ";
        }
        if (check_marka3.isSelected())
        {
            clause += "marka = 'Audi' OR ";
        }
        if (check_marka4.isSelected())
        {
            clause += "marka = 'Mercedes' OR ";
        }
        if (check_marka5.isSelected())
        {
            clause += "marka = 'Porsche' OR ";
        }
        if (check_marka6.isSelected())
        {
            clause += "marka = 'Ford' ";
        }

        if (clause.endsWith(" OR ")) {
            clause = clause.substring(0, clause.length() - 4);
        }
        System.out.println(clause);
        return clause;
    }

    private String getSelectedPaliwoClause() {
        String clause = "";
        if (check_paliwo1.isSelected()) {
            clause += "paliwo = 'Diesel' OR ";
        }
        if (check_paliwo2.isSelected()) {
            clause += "paliwo = 'Benzyna' OR ";
        }
        if (check_paliwo3.isSelected()) {
            clause += "paliwo = 'LPG' OR ";
        }
        if (check_paliwo4.isSelected()) {
            clause += "paliwo = 'Elektryczny' ";
        }

        if (clause.endsWith(" OR ")) {
            clause = clause.substring(0, clause.length() - 4);
        }
        System.out.println(clause);
        return clause;
    }

    private String getSelectedMiejscaClause() {
        String clause = "";
        if (check_miejsca1.isSelected()) {
            clause += "miejsca = '4' OR ";
        }
        if (check_miejsca2.isSelected()) {
            clause += "miejsca = '5' OR ";
        }
        if (check_miejsca3.isSelected()) {
            clause += "miejsca = '6' OR ";
        }
        if (check_miejsca4.isSelected()) {
            clause += "miejsca = '7' ";
        }

        if (clause.endsWith(" OR ")) {
            clause = clause.substring(0, clause.length() - 4);
        }
        System.out.println(clause);
        return clause;
    }

    private String getPriceClause() {
        String lowPrice = text_cena_low.getText();
        String highPrice = text_cena_high.getText();
        String clause = "";

        if (!lowPrice.isEmpty() && !highPrice.isEmpty()) {
            clause = "cena BETWEEN " + lowPrice + " AND " + highPrice;
        } else if (!lowPrice.isEmpty()) {
            clause = "cena >= " + lowPrice;
        } else if (!highPrice.isEmpty()) {
            clause = "cena <= " + highPrice;
        }
        System.out.println(clause);
        return clause;
    }
    private String getPriceClauseOC() {
        String lowPrice = text_ubez_low.getText();
        String highPrice = text_ubez_high.getText();
        String clause = "";

        if (!lowPrice.isEmpty() && !highPrice.isEmpty()) {
            clause = "ubezpieczenie24 BETWEEN " + lowPrice + " AND " + highPrice;
        } else if (!lowPrice.isEmpty()) {
            clause = "ubezpieczenie24 >= " + lowPrice;
        } else if (!highPrice.isEmpty()) {
            clause = "ubezpieczenie24 <= " + highPrice;
        }
        System.out.println(clause);
        return clause;
    }





    @FXML
    private void filterCars(ActionEvent event) {
        try {
            String sql = "SELECT * FROM samochod WHERE ";
            String markaClause = getSelectedMarksClause();
            String paliwoClause = getSelectedPaliwoClause();
            String miejscaClause = getSelectedMiejscaClause();
            String cenaClause = getPriceClause();
            String cenaOCClause = getPriceClauseOC();

            if (!markaClause.isEmpty()) {
                sql += markaClause + " AND ";
            }
            if (!paliwoClause.isEmpty()) {
                sql += paliwoClause + " AND ";
            }
            if (!miejscaClause.isEmpty()) {
                sql += miejscaClause + " AND ";
            }
            if (!cenaClause.isEmpty()) {
                sql += cenaClause + " AND ";
            }
            if (!cenaOCClause.isEmpty()) {
                sql += cenaOCClause + " AND ";
            }
            if (sql.endsWith(" AND ")) {
                sql = sql.substring(0, sql.length() - 5);
            }
            sql +=';';
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);

            carListView.getItems().clear();
            while (resultSet.next()) {
                String tablice = resultSet.getString("tablice");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                String paliwo = resultSet.getString("paliwo");
                int cena = resultSet.getInt("cena");
                int cenaUbezpieczenia = resultSet.getInt("ubezpieczenie24");

                String carData = tablice + " | " + marka + " | " + model + " | " + paliwo + " | " + cena + " | " + cenaUbezpieczenia;
                carListView.getItems().add(carData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheckboxAction(ActionEvent event)
    {
        refreshCarList();
        check_marka1.setSelected(false);
        check_marka2.setSelected(false);
        check_marka3.setSelected(false);
        check_marka4.setSelected(false);
        check_marka5.setSelected(false);
        check_marka6.setSelected(false);

        check_paliwo1.setSelected(false);
        check_paliwo2.setSelected(false);
        check_paliwo3.setSelected(false);
        check_paliwo4.setSelected(false);

        check_miejsca1.setSelected(false);
        check_miejsca2.setSelected(false);
        check_miejsca3.setSelected(false);
        check_miejsca4.setSelected(false);

        text_cena_low.clear();
        text_cena_high.clear();

        text_ubez_low.clear();
        text_ubez_high.clear();
    }



    public void userLogOut(ActionEvent event) throws IOException {
        HelloApplication next = new HelloApplication();
        next.changeScene2("hello-view.fxml");
    }

    public void AddCar(ActionEvent event) throws IOException {
        HelloApplication next = new HelloApplication();
        next.Add_scene("Add_window.fxml");
    }

    public void EditCar(ActionEvent event) throws IOException {
        HelloApplication next = new HelloApplication();
        next.Edit_scene("Edit_window.fxml");
    }

    public void RentCar(ActionEvent event) throws IOException {
        String selectedCarData = carListView.getSelectionModel().getSelectedItem();
        if (selectedCarData != null) {
            String[] carDetails = selectedCarData.split(" \\| ");
            selectedCarTablice = carDetails[0];
            selectedCarMarka = carDetails[1];
            selectedCarModel = carDetails[2];
            selectedCarCena = Integer.parseInt(carDetails[4]);
            selectedCarUbezpieczenie = Integer.parseInt(carDetails[5]);


            //System.out.println(selectedCarTablice);
            HelloApplication next = new HelloApplication();
            next.Rent_scene("Rent_window.fxml");
        } else {
            showAlert("No Car Selected", "Please select a car from the list.");
        }
    }

    public void refreshCarList() {
        try {
            resultSet = statement.executeQuery("SELECT * FROM samochod");

            carListView.getItems().clear();
            while (resultSet.next()) {
                String tablice = resultSet.getString("tablice");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                String paliwo = resultSet.getString("paliwo");
                int cena = resultSet.getInt("cena");
                int cenaUbezpieczenia = resultSet.getInt("ubezpieczenie24");

                String carData = tablice + " | " + marka + " | " + model + " | " + paliwo + " | " + cena + " | " + cenaUbezpieczenia;
                carListView.getItems().add(carData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Metoda dostępna w drugim oknie, aby pobrać wybrany samochód
    public String getSelectedCarTablice() {
        return selectedCarTablice;
    }

    public String getSelectedCarMarka() {
        return selectedCarMarka;
    }

    public String getSelectedCarModel() {

        return selectedCarModel;
    }

    public int getSelectedCarCena() {
        return selectedCarCena;
    }

    public int getSelectedCarUbezpieczenie() {
        return selectedCarUbezpieczenie;
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
}
