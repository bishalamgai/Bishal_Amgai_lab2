package com.example.bishal_amgai_lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public TableView tableView;
    @FXML
    public Label messageText;
    @FXML
    public TableColumn Carid;
    @FXML
    public TableColumn Brand;
    @FXML
    public TableColumn Model;
    @FXML
    public TableColumn Year;
    @FXML
    public TextField Tbrand;
    @FXML
    public TextField Tmodel;
    @FXML
    public TextField Tyear;


    @FXML
    protected void onHelloButtonClick() {
        messageText.setText("Welcome to JavaFX Application!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Carid.setCellValueFactory(new
                PropertyValueFactory<Cars,Integer>("Carid"));
        Brand.setCellValueFactory(new
                PropertyValueFactory<Cars,String>("Brand"));
        Model.setCellValueFactory(new
                PropertyValueFactory<Cars,String>("Model"));
        Year.setCellValueFactory(new
                PropertyValueFactory<Cars,Integer>("Year"));}
    public void ViewData() {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2_bishal_amgai";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `cars`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            tableView.getItems().clear();

// Populate the table with data from the database
            while (resultSet.next()) {
                int Carid = resultSet.getInt("Carid");
                String Brand = resultSet.getString("Brand");
                String Model = resultSet.getString("Model");
                int Year = resultSet.getInt("Year");

                tableView.getItems().add(new Cars(Carid, Brand, Model,
                        Year));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Insert(ActionEvent actionEvent) {
        String tbrand= Tbrand.getText();
        String tmodel= Tmodel.getText();
        String tyear= Tyear.getText();


        InsertTable(tbrand,tmodel,tyear);
    }

    public void InsertTable(String tbrand,String tmodel,String tyear) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2_bishal_amgai";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `cars`( `Brand`, `Model`, `Year`) VALUES ('"+tbrand+"','"+tmodel+"','"+tyear+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            ViewData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void update(ActionEvent event) {
        Cars selectedCar = (Cars) tableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            int carId = selectedCar.getCarid();
            String newBrand = Tbrand.getText();
            String newModel = Tmodel.getText();
            int newYear = Integer.parseInt(Tyear.getText());

            updateInDatabase(carId, newBrand, newModel, newYear);
        }
    }

    @FXML
    protected void delete(ActionEvent event) {
        Cars selectedCar = (Cars) tableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            int carId = selectedCar.getCarid();
            deleteFromDatabase(carId);
        }
    }

    private void insertIntoDatabase(String brand, String model, int year) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2_bishal_amgai";
        String dbUser = "root";
        String dbPassword = "";
        String query = "INSERT INTO cars (Brand, Model, Year) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, year);
            preparedStatement.executeUpdate();
            messageText.setText("Data inserted successfully.");
            ViewData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateInDatabase(int carId, String brand, String model, int year) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2_bishal_amgai";
        String dbUser = "root";
        String dbPassword = "";
        String query = "UPDATE cars SET Brand=?, Model=?, Year=? WHERE Carid=?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, carId);
            preparedStatement.executeUpdate();
            messageText.setText("Data updated successfully.");
            ViewData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromDatabase(int carId) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2_bishal_amgai";
        String dbUser = "root";
        String dbPassword = "";
        String query = "DELETE FROM cars WHERE Carid=?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, carId);
            preparedStatement.executeUpdate();
            messageText.setText("Data deleted successfully.");
            ViewData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}